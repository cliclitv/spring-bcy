package bcy.service;

import java.util.ArrayList;
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

    public Term getTermById(Long id) {
        Term term = termDao.getTermById(id);
        List<String> list = new ArrayList<>();
        List<Post> posts = postDao.getPostsTitle(id, Long.valueOf(1), Long.valueOf(1000));
        for (Post post : posts) {
            list.add(post.getTitle());
        }
        term.setList(list);
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