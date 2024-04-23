package bcy.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentDao {
    public Comment getCommentById(Long id);

    public Integer addComment(Comment comment);

    public Integer updateComment(Comment comment);

}