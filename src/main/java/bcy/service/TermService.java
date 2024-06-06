package bcy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcy.dao.Term;
import bcy.dao.TermDao;

@Service
public class TermService {
    @Autowired
    private TermDao tagDao;

    public void addTag(Term tag){
        tagDao.addCollection(tag.getPid(), tag.getTitle(),0);
    }

    public List<Term> getTags(Long pid){
        return tagDao.getCollections(pid);
    }
}
