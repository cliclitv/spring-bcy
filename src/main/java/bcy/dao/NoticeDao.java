package bcy.dao;

import java.util.List;

public interface NoticeDao {
    public Integer addNotice(Long uid, String action, String source, Long tid);
    public List<Notice> getNotices(Long tid);
}
