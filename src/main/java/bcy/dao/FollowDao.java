package bcy.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FollowDao {
    public Integer addFollow(Long uid, Long tid, Integer friend);
    public Integer deleteFollow(Long uid);
    public Integer updateFollow(Long uid, Integer friend);
    public Follow getFollowByUid(Long uid, Long tid);
}
