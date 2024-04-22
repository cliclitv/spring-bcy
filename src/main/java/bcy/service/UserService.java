package bcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcy.dao.UserDao;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public Long query(Long id){
        return userDao.query(id);
    }
    
}
