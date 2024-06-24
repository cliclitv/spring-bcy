package bcy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TermDao {
    public Integer addTerm(Term team);

    public Integer updateTerm(Term team);
    public Term getTermById(Long id);

    public List<Term> getTerms(String cat, String author, Long uid);
}
