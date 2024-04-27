package bcy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentDao {
    public Comment getCommentById(Long id);

    public Integer addComment(Comment comment);

    public Integer updateComment(Comment comment);

    public List<Comment> getComments(Long pid, Long cid, Long start, Long size);

}