package bcy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagDao {
    public Integer addCollection(Long uid, String title, Integer type);

    public List<Tag> getCollections(Long uid);
}
