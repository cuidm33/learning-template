package cn.tsui.service.impl;

import cn.tsui.dao.UserLikeMapper;
import cn.tsui.dto.LikedCountDTO;
import cn.tsui.dto.UserInfo;
import cn.tsui.enums.LikedStatusEnum;
import cn.tsui.model.UserLike;
import cn.tsui.service.LikedService;
import cn.tsui.service.RedisService;
import cn.tsui.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class LikedServiceImpl implements LikedService {

    @Autowired
    UserLikeMapper userLikeMapper;

    @Autowired
    RedisService redisService;


    @Override
    public UserLike save(UserLike userLike) {
        log.info("save database ---" + userLike);
        userLikeMapper.insert(userLike);
        return userLike;
    }

    @Override
    public int updateByPrimaryKey(UserLike userLike){
        return userLikeMapper.updateByPrimaryKey(userLike);
    }

   /* @Override
    @Transactional
    public List<UserLike> saveAll(List<UserLike> list) {
        for (UserLike like:list) {
            userLikeMapper.insert(like);
        }
        return list;
    }*/

    @Override
    public List<UserLike> getLikedListByLikedUserId(String likedUserId, Pageable pageable) {
        return userLikeMapper.findByLikedUserIdAndStatus(likedUserId, LikedStatusEnum.LIKE.getCode());
    }

    @Override
    public List<UserLike> getLikedListByLikedPostId(String likedPostId, Pageable pageable) {
        return userLikeMapper.findByLikedPostIdAndStatus(likedPostId, LikedStatusEnum.LIKE.getCode());
    }

    @Override
    public UserLike getByLikedUserIdAndLikedPostId(String likedUserId, String likedPostId) {
        return userLikeMapper.findByLikedUserIdAndLikedPostId(likedUserId, likedPostId);
    }

    @Override
    @Transactional
    public void transLikedFromRedis2DB() {
        List<UserLike> list = redisService.getLikedDataFromRedis();
        for (UserLike like : list) {
            System.out.println(list);
            UserLike ul = getByLikedUserIdAndLikedPostId(like.getLikedUserId(), like.getLikedPostId());
            if (ul == null){
                //没有记录，直接存入
                save(like);
            }else{
                //有记录，需要更新
                if(ul.getStatus() != like.getStatus()){//状态相同不需要修改
                    ul.setStatus(like.getStatus());
                    updateByPrimaryKey(ul);
                }
            }
        }
    }

    @Override
    @Transactional
    public void transLikedCountFromRedis2DB() {
        /*List<LikedCountDTO> list = redisService.getLikedCountFromRedis();
        for (LikedCountDTO dto : list) {
            UserInfo user = userService.findById(dto.getId());
            //点赞数量属于无关紧要的操作，出错无需抛异常
            if (user != null){
                Integer likeNum = user.getLikeNum() + dto.getCount();
                user.setLikeNum(likeNum);
                //更新点赞数量
                userService.updateInfo(user);
            }
        }*/
    }
}