package bcy.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcy.dao.Term;
import bcy.dao.TermDao;

@Service
public class TermService {
    @Autowired
    private TermDao termDao;

    public void addTerm(Term term) {
        if (term.getCreateTime() == null) {
            Date now = new Date();
            term.setCreateTime(now);
        }

        if (term.getId() != null) {
            termDao.updateTerm(term);
        } else {
            termDao.addTerm(term);
        }

    }

    public List<Term> getTerms(String cat, String author) {
        List<Term> terms = termDao.getTerms(cat, author);
        return terms;
    }
}