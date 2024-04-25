package bcy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostDao {
    public Post getPostById(Long id);

    public Integer addPost(Post post);

    public Integer updatePost(Post post);

    public List<Post> getPosts(String status, String sort, String[] tags, Long uid, Long pid, String search, Long start, Long size);

}
