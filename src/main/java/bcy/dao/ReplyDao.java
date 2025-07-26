package bcy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyDao {
    public Reply getReplyById(Long id);

    public Integer addReply(Reply reply);

    public Integer updateReply(Reply reply);

    public List<Reply> getReplys(Long pid, Long cid, Long start, Long size);

}