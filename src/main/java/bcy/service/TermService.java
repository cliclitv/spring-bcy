package bcy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcy.dao.Term;
import bcy.dao.TermDao;

@Service
public class TermService {
    @Autowired
    private TermDao termDao;

    public void addTerm(Term term){
        termDao.addTerm(term.getPid(), term.getTitle(),term.getType());
    }

    public List<Term> getTerms(Long pid){
        return termDao.getTerm(pid);
    }
}
