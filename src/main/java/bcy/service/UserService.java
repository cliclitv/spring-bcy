package bcy.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import bcy.dao.User;
import bcy.service.util.ConditionException;
import bcy.service.util.MD5Util;
import bcy.service.util.TokenUtil;
import jakarta.annotation.Resource;

@Service
public class UserService {
    private static String salt = "clicli&bcy@123.";

    @Autowired
    private UserSupport userSupport;

    @Resource
    private JdbcTemplate jdbcTemplate;

    public void addUser(User user) {

        if (user.getId() != null) {
            this.updateUser(user);
            return;
        }

        Map<String, Object> dbUser = this.getUserByEmail(user.getEmail());

        if (dbUser != null) {
            throw new ConditionException("邮箱已注册！");
        }

        String pwd = user.getPwd();

        String md5Pwd = MD5Util.sign(MD5Util.sign(pwd, salt), salt);

        user.setPwd(md5Pwd);

        if (user.getBio() == null) {
            user.setBio("这个人很懒，木有签名~");
        }

        String sql = "insert into user (name, email, pwd, bio, level) values (?, ?, ?, '这个人很懒，木有签名~', 1)";

        Object[] objects = new Object[3];
        objects[0] = user.getName();
        objects[1] = user.getEmail();
        objects[2] = user.getPwd();
        jdbcTemplate.update(sql, objects);

    }

    public String login(User user) {

        String email = user.getEmail();

        Map<String, Object> dbUser = email.contains("@") ? this.getUserByEmail(email) : this.getUserByName(email);

        if (dbUser == null) {
            throw new ConditionException("当前邮箱不存在！");
        }

        String pwd = user.getPwd();

        String md5Pwd = MD5Util.sign(MD5Util.sign(pwd, salt), salt);

        String dbPwd = dbUser.get("pwd").toString();

        if (!md5Pwd.equals(dbPwd)) {
            throw new ConditionException("密码错误！");
        }

        return TokenUtil.generateToken(Integer.parseInt(dbUser.get("id").toString()), Integer.parseInt(dbUser.get("level").toString()));
    }

    public void updateUser(User user) {

        Long currentId = userSupport.getCurrentUserId();
        Integer currentLevel = userSupport.getCurrentUserLevel();

        Map<String, Object> dbUser = this.getUserById(user.getId());

        if (dbUser == null) {
            throw new ConditionException("用户不存在");
        }

        if (dbUser.get("id") != currentId && currentLevel < 4) { // 不是当前用户或管理
            throw new ConditionException("没有权限");
        }

        String pwd = user.getPwd();
        if (pwd == null || pwd == "") { // 密码留空
            user.setPwd(dbUser.get("pwd").toString());
        } else {
            String md5Pwd = MD5Util.sign(MD5Util.sign(pwd, salt), salt);
            user.setPwd(md5Pwd);
        }

        String sql = "update user set name=?,email=?, pwd=?, bio=?, level=? where id = ?";

        Object[] objects = new Object[6];
        objects[0] = user.getName();
        objects[1] = user.getEmail();
        objects[2] = user.getPwd();
        objects[3] = user.getBio();
        objects[4] = user.getLevel();
        objects[5] = user.getId();
        jdbcTemplate.update(sql, objects);

    }

    public Map<String, Object> getUserByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        try {
            return jdbcTemplate.queryForMap(sql, email);
        } catch (EmptyResultDataAccessException e) {
            return null; // 没有找到用户
        }
    }

    public Map<String, Object> getUserByName(String name) {
        String sql = "SELECT * FROM user WHERE name = ?";
        try {
            return jdbcTemplate.queryForMap(sql, name);
        } catch (EmptyResultDataAccessException e) {
            return null; // 没有找到用户
        }
    }

    public Map<String, Object> getUserById(Long id) {
        if (id == 0) {
            id = userSupport.getCurrentUserId();
        }
        String sql = "SELECT * FROM user WHERE id = ?";
        try {
            return jdbcTemplate.queryForMap(sql, id);
        } catch (EmptyResultDataAccessException e) {
            return null; // 没有找到用户
        }
    }

}
