package bcy.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostDao {
    public Post getPostById(Long id);

    public Integer addPost(Post post);

    public Integer updatePost(Post post);

}
