package bcy.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcy.dao.Comment;
import bcy.dao.CommentDao;
import bcy.service.util.ConditionException;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;

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

    public List<Comment> getComments(Long pid, Long cid, Long page, Long size) {
        Long start = size * (page - 1);
        List<Comment> comments = commentDao.getComments(pid, cid, start, size);
        List<Comment> list = new ArrayList<>();
        Map<Long, Comment> commentMap = new HashMap<>();

        for (Comment comment : comments) {
            Long parentid = comment.getCid();
            Long id = comment.getId();
            if (parentid == 0) {
                list.add(comment);
            }
            commentMap.put(id, comment);
        }
        for (Comment comment : comments) {
            Long parentid = comment.getCid();

            if (parentid != 0) {
                Comment parent = commentMap.get(parentid);
                parent.getReplies().add(comment);
            }
        }
        return list;
    }

}
