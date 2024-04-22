package bcy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bcy.service.UserService;


@RestController
public class HelloWorld {
    @Autowired
    private UserService userService;

    @RequestMapping("/query")
    public Long query(Long id){
        return userService.query(id);
    }
}
