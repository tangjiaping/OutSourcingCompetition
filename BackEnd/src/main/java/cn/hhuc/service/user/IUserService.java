package cn.hhuc.service.user;

import cn.hhuc.bean.user.User;

public interface IUserService {
    public User queryUserByName(String username,String password);
    public void insertUser(User user);
    public boolean isAccess(String phone);
    public User queryUserByPhone(String phone);
}
