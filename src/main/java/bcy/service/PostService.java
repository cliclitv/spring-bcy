package bcy.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import bcy.dao.Post;
import bcy.service.util.ConditionException;
import jakarta.annotation.Resource;

@Service
public class PostService {

    @Autowired
    private UserSupport userSupport;

    @Resource
    private JdbcTemplate jdbcTemplate;

    public void addPost(Post post) {

        if (post.getId() != null) {
            updatePost(post);
            return;
        }

        Map<String, Object> dbPost = this.getPostById(post.getId());

        if (dbPost != null) {
            throw new ConditionException("文章已存在");
        }

        Date now = new Date();

        post.setCreateTime(now);

        String sql = "insert into posts (title, content, tag, uid, createTime, updateTime,pv) values (?, ?, ?, ?, ?, ?,0)";

        Object[] objects = new Object[6];
        objects[0] = post.getTitle();
        objects[1] = post.getContent();
        objects[2] = post.getTag();
        objects[3] = post.getUid();
        objects[4] = post.getCreateTime();
        objects[5] = post.getCreateTime();
        jdbcTemplate.update(sql, objects);

    }

    public void updatePost(Post post) {

        Long currentId = userSupport.getCurrentUserId();
        Integer currentLevel = userSupport.getCurrentUserLevel();

        Map<String, Object> dbPost = this.getPostById(post.getId());

        if (dbPost == null) {
            throw new ConditionException("文章不存在");
        }

        if (dbPost.get("uid") != currentId && currentLevel < 4) { // 不是当前用户或管理
            throw new ConditionException("没有权限");
        }

        Date now = new Date();

        post.setUpdateTime(now);

        String sql = "update posts set title=?, content=?, tag=?, updateTime=? where id = ?";

        Object[] objects = new Object[5];
        objects[0] = post.getTitle();
        objects[1] = post.getContent();
        objects[2] = post.getTag();
        objects[3] = post.getUpdateTime();
        objects[4] = post.getId();
        jdbcTemplate.update(sql, objects);

    }

    public List<Map<String, Object>> getPosts(String tag, Long uid, Long pid, String search, Long page,
            Long size, Long fid) {
        String[] tags = new String[0];
        if (tag != null) {
            tags = tag.split(",");
        }
        Long start = size * (page - 1);

        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<>();

        sql.append(
                "select posts.id,posts.title,posts.content,posts.tag,posts.createTime,posts.updateTime,posts.pv,posts.uid,users.name,users.email from post LEFT JOIN users ON posts.uid = users.id where title != '删除'"); // 基础查询

        if (tags != null && tags.length > 0) {
            sql.append(" AND (1=2");
            for (String t : tags) {
                sql.append(" OR tag LIKE ?");
                params.add("%" + tag + "%");
            }
            sql.append(") ");
        }

        sql.append("order by createTime asc limit ?, ?");
        params.add(start);
        params.add(size);

        return jdbcTemplate.queryForList(sql.toString(), params.toArray());
    }

    public Map<String, Object> getPostById(Long id) {
        String sql = "SELECT * FROM post WHERE id = ?";
        try {
            return jdbcTemplate.queryForMap(sql, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Long replacePv(Long pid) {
        Map<String, Object> dbPost = this.getPostById(pid);
        Long pv = (Long) dbPost.get("pv") + 1;
        String sql = "update posts set pv=#{pv} where id = ?";
        jdbcTemplate.update(sql, pid);
        return pv;
    }

}
