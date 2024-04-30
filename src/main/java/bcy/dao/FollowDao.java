package bcy.dao;

public interface FollowDao {
    public Integer addFollow(Long uid, Long tid);
    public Integer eachFollow(Long uid, Long tid);
    public Integer deleteFollow(Long uid);
    public Integer updateFollow(Long uid, Integer each);
    public Follow getFollowByUid(Long uid);
}
