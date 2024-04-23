package bcy.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostDao {
    public User getPostById(Long id);

    public Integer addPost(User user);

    public Integer updatePost(User user);

}
