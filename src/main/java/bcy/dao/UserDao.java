package bcy.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    public Long query (Long id);
    
}