package bcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcy.dao.User;
import bcy.dao.UserDao;
import bcy.service.util.ConditionException;
import bcy.service.util.MD5Util;
import bcy.service.util.TokenUtil;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    private static String salt = "clicli&bcy@123.";

    public Long query(Long id) {
        return userDao.query(id);
    }

    public void addUser(User user) {

        User dbUser = this.getUserByEmail(user.getEmail());

        if (dbUser != null) {
            throw new ConditionException("邮箱已注册！");
        }

        String pwd = user.getPwd();

        String md5Pwd = MD5Util.sign(MD5Util.sign(pwd, salt), salt);

        user.setPwd(md5Pwd);

        if (user.getSign() == null) {
            user.setSign("这个人很懒，木有签名~");
        }

        userDao.addUser(user);

    }

    public String login(User user) {

        User dbUser = this.getUserByEmail(user.getEmail());

        if (dbUser == null) {
            throw new ConditionException("当前邮箱不存在！");
        }

        String pwd = user.getPwd();

        String md5Pwd = MD5Util.sign(MD5Util.sign(pwd, salt), salt);

        String dbPwd = dbUser.getPwd();

        if (!md5Pwd.equals(dbPwd)) {
            throw new ConditionException("密码错误！");
        }

        return TokenUtil.generateToken(dbUser.getId());
    }

    public void updateUser(User user) {

        User dbUser = this.getUserById(user.getId());

        if (dbUser == null) {
            throw new ConditionException("用户不存在");
        }

        String pwd = user.getPwd();
        if (pwd == null || pwd == "") { // 密码留空
            user.setPwd(dbUser.getPwd());
        } else {
            String md5Pwd = MD5Util.sign(MD5Util.sign(pwd, salt), salt);
            user.setPwd(md5Pwd);
        }

        userDao.updateUser(user);

    }

    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

}
