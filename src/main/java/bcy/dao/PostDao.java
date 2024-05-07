package bcy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostDao {
    public Post getPostById(Long id);

    public Integer addPost(Post post);

    public Integer updatePost(Post post);

    public Integer replacePv(Long pid, Long pv);

    public List<Post> getPosts(String[] tags, Long uid, Long pid, String search, Long start,
            Long size, Long fid);

}
