package cn.tsui;

import cn.tsui.dao.UserLikeMapper;
import cn.tsui.enums.LikedStatusEnum;
import cn.tsui.model.UserLike;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserLikeTest {

    @Resource
    UserLikeMapper userLike;

    @Test
    public void selectTest01(){
        List<UserLike> userLikes= userLike.selectAll();
        System.out.println(userLikes);
    }

    @Test
    public void findByLikedUserIdAndStatus(){
        List<UserLike> userLikes= userLike.findByLikedUserIdAndStatus("2333", LikedStatusEnum.LIKE.getCode());
        System.out.println(userLikes);
    }

    @Test
    public void findByLikedUserIdAndLikedPostId(){
        UserLike userLikes= userLike.findByLikedUserIdAndLikedPostId("2333","2444");
        System.out.println(userLikes);
        UserLike userLikes1= userLike.findByLikedUserIdAndLikedPostId("123","321");
        System.out.println(userLikes1);
    }

    @Test
    public void findByLikedPostIdAndStatus(){
        List<UserLike> userLikes= userLike.findByLikedPostIdAndStatus("233", LikedStatusEnum.LIKE.getCode());
        System.out.println(userLikes);
    }
}
