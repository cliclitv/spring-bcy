package bcy.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import bcy.dao.Reply;
import bcy.service.util.ConditionException;
import jakarta.annotation.Resource;

@Service
public class ReplyService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public void addReply(Reply reply) {

        Map<String, Object> dbReply = this.getReplyById(reply.getId());

        if (dbReply != null) {
            throw new ConditionException("评论已存在");
        }

        Date now = new Date();

        reply.setCreateTime(now);

        String sql = "insert into reply (content, pid, uid, rid, createTime) values (?, ?, ?, ?, ?)";

        Object[] objects = new Object[3];
        objects[0] = reply.getContent();
        objects[1] = reply.getPid();
        objects[2] = reply.getUid();
        objects[3] = reply.getRid();
        objects[4] = reply.getCreateTime();
        jdbcTemplate.update(sql, objects);
    }

    public Map<String, Object> getReplyById(Long id) {

        String sql = "SELECT * FROM reply WHERE id = ?";
        try {
            return jdbcTemplate.queryForMap(sql, id);
        } catch (EmptyResultDataAccessException e) {
            return null; // 没有找到用户
        }
    }

    public List<Map<String, Object>> getReplies(Long pid, Long rid, Long page, Long size) {
        Long start = size * (page - 1);

        String sql = "select * from reply LEFT JOIN user ON reply.uid = user.id where pid=? order by createTime desc limit ?, ?";
        Object[] objects = new Object[3];
        objects[0] = pid;
        objects[1] = start;
        objects[2] = size;

        List<Map<String, Object>> replys = jdbcTemplate.queryForList(sql, objects);

        List<Map<String, Object>> list = new ArrayList<>();
        Map<Long, Map<String, Object>> replyMap = new HashMap<>();

        for (Map<String, Object> reply : replys) {
            Long parentid =(Long) reply.get("rid");
            Long id = (Long) reply.get("id");
            if (parentid == 0) {
                list.add(reply);
            }
            replyMap.put(id, reply);
        }
        for (Map<String, Object> reply : replys) {
            Long parentid = (Long) reply.get("rid");

            if (parentid != 0) {
                Map<String, Object> parent = replyMap.get(parentid);
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> replies = (List<Map<String, Object>>) parent.get("replies");
            replies.add(reply);
                replies.add(reply);
            }
        }
        return list;
    }

}
