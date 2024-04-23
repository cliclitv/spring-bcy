package bcy.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcy.dao.Post;
import bcy.dao.PostDao;
import bcy.service.util.ConditionException;

@Service
public class PostService {
    @Autowired
    private PostDao postDao;

    @Autowired
    private UserSupport userSupport;

    public void addPost(Post post) {

        Post dbPost = this.getPostById(post.getId());

        if (dbPost != null) {
            throw new ConditionException("文章已存在");
        }

        Date now = new Date();

        post.setCreateTime(now);

        postDao.addPost(post);

    }

    public void updatePost(Post post) {

        Long currentId = userSupport.getCurrentUserId();
        Integer currentLevel = userSupport.getCurrentUserLevel();

        Post dbPost = this.getPostById(post.getId());

        if (dbPost == null) {
            throw new ConditionException("文章不存在");
        }

        if (dbPost.getUid() != currentId && currentLevel < 4) { // 不是当前用户或管理
            throw new ConditionException("没有权限");
        }

        Date now = new Date();

        post.setUpdateTime(now);

        postDao.updatePost(post);

    }

    public Post getPostById(Long id) {
        return postDao.getPostById(id);
    }

}
