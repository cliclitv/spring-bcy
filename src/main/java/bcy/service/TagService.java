package bcy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcy.dao.Tag;
import bcy.dao.TagDao;

@Service
public class TagService {
    @Autowired
    private TagDao tagDao;

    public void addTag(Tag tag){
        tagDao.addCollection(tag.getPid(), tag.getTitle(),0);
    }

    public List<Tag> getTags(Long pid){
        return tagDao.getCollections(pid);
    }
}
