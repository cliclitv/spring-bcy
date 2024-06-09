package bcy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TermDao {
    public Integer addTerm(Long uid, String title, String type);

    public List<Term> getTerm(Long uid);
}
