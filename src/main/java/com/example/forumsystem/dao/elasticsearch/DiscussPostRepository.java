package com.example.forumsystem.dao.elasticsearch;

import com.example.forumsystem.pojo.DiscussPost;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sparkle6979l
 * @version 1.0
 * @data 2023/5/23 12:18
 */
@Repository
public interface DiscussPostRepository extends ElasticsearchRepository<DiscussPost,Integer> {

}
