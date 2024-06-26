package bcy.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Term getTermById(Long id) {
        Term term = termDao.getTermById(id);
        List<Post> chapters = postDao.getPostsTitle(id, Long.valueOf(1), Long.valueOf(1000));
        term.setChapters(chapters);
        return term;
    }

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

    public List<Term> getTerms(String cat, String author, Long uid) {
        List<Term> terms = termDao.getTerms(cat, author, uid);
        return terms;
    }
}