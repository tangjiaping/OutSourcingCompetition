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
        if (password != null && password.equals(user.getPassword())){
            return user;
        }
        return null;
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public String queryUserByPhone(String phone) {
        String username = userMapper.queryUserByPhone(phone);
        if (null != username){

        }
        return null;
    }
}
