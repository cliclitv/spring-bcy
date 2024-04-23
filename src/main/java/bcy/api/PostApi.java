package bcy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import bcy.dao.JsonResponse;
import bcy.dao.User;
import bcy.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class PostApi {
    @Autowired
    private UserService userService;

    @GetMapping("/post/{id}")
    public JsonResponse<User> getCurrentUser(@PathVariable String id) {
        User user = userService.getUserById(Long.valueOf(id));
        return new JsonResponse<>(user);
    }

    @PostMapping("/post")
    public JsonResponse<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return JsonResponse.success();

    }

    @PutMapping("/post/{id}")
    public JsonResponse<String> putMethodName(@PathVariable String id, @RequestBody User user) {
        user.setId(Long.valueOf(id));

        userService.updateUser(user);

        return JsonResponse.success();
    }

}
