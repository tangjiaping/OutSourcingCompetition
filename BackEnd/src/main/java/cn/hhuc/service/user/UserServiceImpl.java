package cn.hhuc.service.user;


import cn.hhuc.bean.user.User;
import cn.hhuc.mapper.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUser userMapper;

    @Override
    public User queryUserByName(String username,String password) {
        User user = userMapper.queryUserByName(username);
        System.out.println(user);
        if (user != null && password != null && user.getPassword() != null){
            return user;
        }
        return null;
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public boolean isAccess(String phone) {
        User user = queryUserByPhone(phone);
        if (user != null)
            return true;
        return false;
    }

    @Override
    public User queryUserByPhone(String phone) {
        User user = userMapper.queryUserByPhone(phone);
        return user;
    }
}
