package com.example.forumsystem.service;

import com.example.forumsystem.dao.elasticsearch.DiscussPostRepository;
import com.example.forumsystem.pojo.DiscussPost;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sparkle6979l
 * @version 1.0
 * @data 2023/5/23 17:26
 */
@Service
public class ElasticSearchService {

    @Autowired
    private DiscussPostRepository discussRepository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public void saveDiscussPost(DiscussPost post) {
        discussRepository.save(post);
    }

    public void deleteDiscussPost(int id) {
        discussRepository.deleteById(id);
    }

    public Page<DiscussPost> searchDiscussPost(String keyword,int current,int limit){
        //构建搜索条件
        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery(keyword, "title", "content"))
                .withSorts(SortBuilders.fieldSort("type").order(SortOrder.DESC))
                .withSorts(SortBuilders.fieldSort("score").order(SortOrder.DESC))
                .withSorts(SortBuilders.fieldSort("createTime").order(SortOrder.DESC))
                .withPageable(PageRequest.of(current, limit))
                .withHighlightFields(
                        new HighlightBuilder.Field("title").preTags("<em>").postTags("</em>"),
                        new HighlightBuilder.Field("content").preTags("<em>").postTags("</em>")
                ).build();
        SearchHits<DiscussPost> search = elasticsearchRestTemplate.search(build, DiscussPost.class);
        SearchPage<DiscussPost> page = SearchHitSupport.searchPageFor(search, build.getPageable());

        //下面是封装将高亮部分成一个page对象，也可以不做，直接discussPostSearchHit.getHighlightFields()获取
        List<DiscussPost> list = new ArrayList<>();
        for (SearchHit<DiscussPost> discussPostSearchHit : page) {
            DiscussPost discussPost = discussPostSearchHit.getContent();
            //discussPostSearchHit.getHighlightFields() //高亮
            if (discussPostSearchHit.getHighlightFields().get("title") != null) {
                discussPost.setTitle(discussPostSearchHit.getHighlightFields().get("title").get(0));
            }
            if (discussPostSearchHit.getHighlightFields().get("content") != null) {
                discussPost.setContent(discussPostSearchHit.getHighlightFields().get("content").get(0));
            }
            //System.out.println(discussPostSearchHit.getContent());
            list.add(discussPost);
        }
        PageImpl<DiscussPost> pageInfo = new PageImpl<DiscussPost>(list, build.getPageable(), search.getTotalHits());
        return pageInfo;
    }
}
