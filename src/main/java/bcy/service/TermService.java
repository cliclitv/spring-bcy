package bcy.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcy.dao.Comment;
import bcy.dao.Post;
import bcy.dao.PostDao;
import bcy.dao.Term;
import bcy.dao.TermDao;

@Service
public class TermService {
    @Autowired
    private TermDao termDao;
    @Autowired
    private PostDao postDao;

    public void addTerm(Term term) {
        Date now = new Date();

        term.setCreateTime(now);

        termDao.addTerm(term);
    }

    public void updateTerm(Term term) {
        Date now = new Date();

        term.setCreateTime(now);

        termDao.updateTerm(term);
    }

    public List<Term> getTerms(Long pid) {
        List<Term> terms = termDao.getTerms(pid);
        return terms;
    }
}