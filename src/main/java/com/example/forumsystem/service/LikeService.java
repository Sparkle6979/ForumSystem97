package com.example.forumsystem.service;

import com.example.forumsystem.utils.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

/**
 * @author sparkle6979l
 * @version 1.0
 * @data 2023/4/23 22:11
 */
@Service
public class LikeService {
    @Autowired
    private RedisTemplate redisTemplate;


    // 点赞
    public void like(int userId, int entityType, int entityId,int entityUserId) {
            // 需要保证事务性
            // 编程式事务

//            String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);
//            boolean isMember = redisTemplate.opsForSet().isMember(entityLikeKey, userId);
//
//            if (isMember) {
//                redisTemplate.opsForSet().remove(entityLikeKey, userId);
//            } else {
//                redisTemplate.opsForSet().add(entityLikeKey, userId);
//            }

        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);
                String userLikeKey = RedisKeyUtil.getUserLikeKey(entityUserId);

                // 提前进行查询，再开启事务
                boolean isMember = operations.opsForSet().isMember(entityLikeKey, userId);
                operations.multi();

                if (isMember) {
                    operations.opsForSet().remove(entityLikeKey, userId);
                    operations.opsForValue().decrement(userLikeKey);
                } else {
                    operations.opsForSet().add(entityLikeKey, userId);
                    operations.opsForValue().increment(userLikeKey);
                }

                return operations.exec();
            }
        });

    }

    // 查询某实体点赞的数量
    public long findEntityLikeCount(int entityType, int entityId) {
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);
        return redisTemplate.opsForSet().size(entityLikeKey);
    }

    // 查询某人对某实体的点赞状态
    public int findEntityLikeStatus(int userId, int entityType, int entityId) {
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);
        return redisTemplate.opsForSet().isMember(entityLikeKey, userId) ? 1 : 0;
    }

    // 查询某个用户获得的赞
    public int findUserLikeCount(int userId) {
        String userLikeKey = RedisKeyUtil.getUserLikeKey(userId);
        Integer count = (Integer) redisTemplate.opsForValue().get(userLikeKey);
        return count == null ? 0 : count.intValue();
    }
}
