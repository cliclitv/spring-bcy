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
            this.deleteFollow(follow); // 取关
            if (dbFollow2 != null) { // 更新 friend
                this.updateFollow(follow.getTid(), 0);
            }
            return;
        }
        if (dbFollow2 != null) {
            // 互关
            this.addFollow(follow.getUid(), follow.getTid(), 1);
            this.updateFollow(follow.getTid(), 1);
            return;
        }
        // 普通关注
        this.addFollow(follow.getUid(), follow.getTid(), 0);
    }

    public Integer getFollowStatus(Follow follow) {
        Follow dbFollow = this.getFollowByUid(follow.getUid(), follow.getTid());

        if (dbFollow != null) {
            // 互关
            if (dbFollow.getFriend() == 1) {
                return 2;
            } else {
                return 1; // 已关注
            }

        }
        return 0;
    }

    public void addFollow(Long uid, Long tid, Integer friend) {
        followDao.addFollow(uid, tid, friend);
    }

    public void deleteFollow(Follow follow) {
        followDao.deleteFollow(follow.getUid());

    }

    public void updateFollow(Long uid, Integer friend) {
        followDao.updateFollow(uid, friend);
    }

    public Follow getFollowByUid(Long uid, Long tid) {
        return followDao.getFollowByUid(uid, tid);
    }
}
