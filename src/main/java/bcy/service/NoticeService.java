package bcy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcy.dao.Notice;
import bcy.dao.NoticeDao;

@Service
public class NoticeService {
    @Autowired
    private NoticeDao noticeDao;

    public void addNotice(Notice notice){
        noticeDao.addNotice(notice.getUid(), notice.getAction(), notice.getSource(), notice.getTid());
    }

    public List<Notice> getNotices(Long tid){
        return noticeDao.getNotices(tid);
    }
}
