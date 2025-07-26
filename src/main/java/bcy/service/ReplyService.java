package bcy.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcy.dao.Reply;
import bcy.dao.ReplyDao;
import bcy.service.util.ConditionException;

@Service
public class ReplyService {
    @Autowired
    private ReplyDao replyDao;

    public void addReply(Reply reply) {

        Reply dbReply = this.getReplyById(reply.getId());

        if (dbReply != null) {
            throw new ConditionException("评论已存在");
        }

        Date now = new Date();

        reply.setCreateTime(now);

        replyDao.addReply(reply);

    }

    public Reply getReplyById(Long id) {
        return replyDao.getReplyById(id);
    }

    public List<Reply> getReplys(Long pid, Long cid, Long page, Long size) {
        Long start = size * (page - 1);
        List<Reply> replys = replyDao.getReplys(pid, cid, start, size);
        List<Reply> list = new ArrayList<>();
        Map<Long, Reply> replyMap = new HashMap<>();

        for (Reply reply : replys) {
            Long parentid = reply.getCid();
            Long id = reply.getId();
            if (parentid == 0) {
                list.add(reply);
            }
            replyMap.put(id, reply);
        }
        for (Reply reply : replys) {
            Long parentid = reply.getCid();

            if (parentid != 0) {
                Reply parent = replyMap.get(parentid);
                parent.getReplies().add(reply);
            }
        }
        return list;
    }

}
