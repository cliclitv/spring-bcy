package bcy.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FollowDao {
    public Integer addFollow(Long uid, String type, Long tid, Integer friend);

    public Integer deleteFollow(Long uid, String type, Long tid);

    public Integer updateFollow(Long uid, String type, Long tid, Integer friend);

    public Follow getFollow(Long uid, Long tid);

    public Follow getCollect(Long uid, Long tid);
}
