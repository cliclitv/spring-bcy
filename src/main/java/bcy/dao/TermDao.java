package bcy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TermDao {
    public Integer addCollection(Long uid, String title, Integer type);

    public List<Term> getCollections(Long uid);
}
