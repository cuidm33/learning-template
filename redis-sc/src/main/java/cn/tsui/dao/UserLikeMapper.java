package cn.tsui.dao;

import cn.tsui.model.UserLike;
import java.util.List;

public interface UserLikeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLike record);

    UserLike selectByPrimaryKey(Integer id);

    List<UserLike> selectAll();

    int updateByPrimaryKey(UserLike record);

    List<UserLike> findByLikedUserIdAndStatus(String likedUserId,Integer code);

    UserLike findByLikedUserIdAndLikedPostId(String likedUserId,String likedPostId);

    List<UserLike> findByLikedPostIdAndStatus(String likedPostId,Integer code);

}