package bcy.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    public User getUserByEmail(String email);

    public User getUserById(Long id);

    public User getUserByName(String name);

    public Integer addUser(User user);

    public Integer updateUser(User user);

}
