package bcy.service;

import org.springframework.beans.factory.annotation.Autowired;

import bcy.dao.Follow;
import bcy.dao.FollowDao;

public class FollowService {
    @Autowired
    private FollowDao followDao;

    public void replaceFollow(Follow follow) {
        Follow dbFollow = this.getFollowByUid(follow.getUid());
        if (dbFollow != null) {
            this.deleteFollow(follow); // 取关
        }
        Follow dbFollow2 = this.getFollowByUid(follow.getTid());
        if (dbFollow2 != null) {
            // 互关
            this.eachFollow(follow);
        }
        // 普通关注
        this.addFollow(follow);
    }

    public void addFollow(Follow follow) {
        followDao.addFollow(follow.getUid(), follow.getTid());
    }

    public void eachFollow(Follow follow) {
        followDao.addFollow(follow.getUid(), follow.getTid());
        followDao.updateFollow(follow.getUid(), 1);
        followDao.updateFollow(follow.getTid(), 1);
    }

    public void deleteFollow(Follow follow) {
        followDao.deleteFollow(follow.getUid());
    }

    public void updateFollow(Long uid, Integer each) {
        followDao.updateFollow(uid, each);
    }

    public Follow getFollowByUid(Long uid) {
        return followDao.getFollowByUid(uid);
    }
}
