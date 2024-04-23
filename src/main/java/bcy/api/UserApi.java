package bcy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bcy.dao.JsonResponse;
import bcy.dao.User;
import bcy.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class UserApi {
    @Autowired
    private UserService userService;

    @Autowired
    private UserSupport userSupport;

    @RequestMapping("/query")
    public Long query(Long id) {
        return userService.query(id);
    }

    @GetMapping("/user")
    public JsonResponse<User> getCurrentUser(@RequestParam String param) {
        Long uid = userSupport.getCurrentUserId();
        User user = userService.getUserById(uid);
        return new JsonResponse<>(user);

    }
    

    @PostMapping("/register")
    public JsonResponse<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return JsonResponse.success();

    }

    @PostMapping("/login")
    public JsonResponse<String> login(@RequestBody User user) {
        String token = userService.login(user);
        return new JsonResponse<>(token);

    }

}
