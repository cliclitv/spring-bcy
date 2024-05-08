package bcy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface NoticeDao {
    public Integer addNotice(Long uid, String action, String source, Long tid);
    public List<Notice> getNotices(Long tid);
}
