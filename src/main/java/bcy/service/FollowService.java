package bcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcy.dao.Follow;
import bcy.dao.FollowDao;

@Service
public class FollowService {
    @Autowired
    private FollowDao followDao;

    public void replaceFollow(Follow follow) {
        Follow dbFollow = this.getFollowByUid(follow.getUid(), follow.getTid());
        Follow dbFollow2 = this.getFollowByUid(follow.getTid(), follow.getUid());

        if (dbFollow != null) {
            this.deleteFollow(follow.getUid(), "follow", follow.getTid()); // 取关
            if (dbFollow2 != null) { // 更新 friend
                this.updateFollow(follow.getTid(), follow.getTid(), 0);
            }
            return;
        }
        if (dbFollow2 != null) {
            // 互关
            this.addFollow(follow.getUid(), "follow", follow.getTid(), 1);
            this.updateFollow(follow.getTid(), follow.getTid(), 1);
            return;
        }
        // 普通关注
        this.addFollow(follow.getUid(), "follow", follow.getTid(), 0);
    }

    public void replaceCollect(Follow follow) {
        Follow dbCollect = this.getCollectByUid(follow.getUid(), follow.getTid());

        if (dbCollect != null) {
            this.deleteFollow(follow.getUid(), "collect", follow.getTid()); // 取消收藏
            return;
        }
        // 普通关注
        this.addFollow(follow.getUid(), "collect", follow.getTid(), 0);
    }

    public String getFollowStatus(Long uid, Long tid) {
        Follow dbFollow = this.getFollowByUid(uid, tid);

        if (dbFollow != null) {
            if (dbFollow.getFriend() == 1) {
                return "互相关注"; // 互关
            } else {
                return "已关注"; // 已关注
            }
        }
        return "关注";
    }

    public String getCollectStatus(Long uid, Long tid) {
        Follow dbFollow = this.getCollectByUid(uid, tid);

        if (dbFollow != null) {
            return "已收藏"; // 互关
        }
        return "收藏";
    }

    public void addFollow(Long uid, String type, Long tid, Integer friend) {
        followDao.addFollow(uid, type, tid, friend);
    }

    public void deleteFollow(Long uid, String type, Long tid) {
        followDao.deleteFollow(uid, type, tid);

    }

    public void updateFollow(Long uid, Long tid, Integer friend) {
        followDao.updateFollow(uid, "follow", tid, friend);
    }

    public Follow getFollowByUid(Long uid, Long tid) {
        return followDao.getFollow(uid, tid);
    }

    public Follow getCollectByUid(Long uid, Long tid) {
        return followDao.getCollect(uid, tid);
    }
}
