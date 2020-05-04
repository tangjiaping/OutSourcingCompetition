package cn.hhuc.mapper;

import cn.hhuc.bean.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface IUser {
    public User queryUserByName(String username);

    public void insertUser(User user);

    public String queryUserByPhone(String phone);

}
