package cn.tsui.service;

import cn.tsui.dto.UserInfo;

public interface UserService {

    UserInfo findById(int i);

    void updateInfo(UserInfo user);
}
