package bcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcy.dao.User;
import bcy.dao.UserDao;
import bcy.service.util.MD5Util;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public Long query(Long id) {
        return userDao.query(id);
    }

    public void addUser(User user) {

        User dbUser = this.getUserByEmail(user.getEmail());

        if (dbUser != null) {
            // 报错
        }

        String salt = "clicli&bcy@123.";
        String pwd = user.getPwd();

        String md5Pwd = MD5Util.sign(pwd, salt, "UTF-8");

        user.setPwd(md5Pwd);

        if (user.getSign() == null) {
            user.setSign("这个人很懒，木有签名~");
        }

        userDao.addUser(user);

    }

    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

}
