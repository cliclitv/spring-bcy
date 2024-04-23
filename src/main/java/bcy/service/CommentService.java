package bcy.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcy.dao.Comment;
import bcy.dao.CommentDao;
import bcy.service.util.ConditionException;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private UserSupport userSupport;

    public void addComment(Comment comment) {

        Comment dbComment = this.getCommentById(comment.getId());

        if (dbComment != null) {
            throw new ConditionException("评论已存在");
        }

        Date now = new Date();

        comment.setCreateTime(now);

        commentDao.addComment(comment);

    }

    public Comment getCommentById(Long id) {
        return commentDao.getCommentById(id);
    }

}
