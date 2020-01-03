package cn.tsui.controller;

import cn.tsui.model.UserLike;
import cn.tsui.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("liked")
public class LikedController {

    @Autowired
    RedisService redisService;

    /**
     * 点赞。状态为1
     *
     * @param likedUserId
     * @param likedPostId
     */
    @RequestMapping("liked")
    public void liked(String likedUserId,String likedPostId){
        redisService.saveLiked2Redis(likedUserId,likedPostId);
    }

    /**
     * 取消点赞。将状态改变为0
     * @param likedUserId
     * @param likedPostId
     */
    @RequestMapping("unliked")
    public void unlike(String likedUserId, String likedPostId){
        redisService.unlikeFromRedis(likedUserId,likedPostId);
    }

    /*public String getAllLlikedUserId(String likedUserId){
        return null;
    }

    public String getAllLikedUser(String likedPostId){
        return null;
    }

    //@RequestMapping("getLikedDataFromRedis")
    public List<UserLike> getLikedDataFromRedis(){
        return redisService.getLikedDataFromRedis();
    }*/
}
