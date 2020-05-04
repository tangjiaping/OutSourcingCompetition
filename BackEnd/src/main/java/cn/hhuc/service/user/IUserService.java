package cn.hhuc.service.user;

import cn.hhuc.bean.user.User;

public interface IUserService {
    public User queryUserByName(String username,String password);
    public void insertUser(User user);

    public String queryUserByPhone(String phone);
}
