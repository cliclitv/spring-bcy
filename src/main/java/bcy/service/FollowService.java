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
        Follow dbFollow = this.getFollowByUid(follow.getUid());
        Follow dbFollow2 = this.getFollowByUid(follow.getTid());

        if (dbFollow != null) {
            this.deleteFollow(follow); // 取关
            if (dbFollow2 != null) { // 更新 each
                this.updateFollow(follow.getTid(), 0);
            }
        }
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
        // 更新 each
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
