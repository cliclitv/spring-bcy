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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


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
    public JsonResponse<User> getCurrentUser() {
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

    @PutMapping("/user/{id}")
    public JsonResponse<String> putMethodName(@PathVariable String id, @RequestBody User user) {
        user.setId(Long.valueOf(id));

        userService.updateUser(user);
        
        return JsonResponse.success();
    }

}
