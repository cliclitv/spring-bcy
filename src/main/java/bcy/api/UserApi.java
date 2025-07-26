package bcy.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

import bcy.dao.JsonResponse;
import bcy.dao.Post;
import bcy.dao.User;
import bcy.service.UserService;
import jakarta.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin(origins = "*")
public class UserApi {
    @Autowired
    private UserService userService;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/user/{id}")
    public JsonResponse<User> getUser(@PathVariable String id) {
        User user = userService.getUserById(Long.valueOf(id));
        return new JsonResponse<>(user);
    }

    @PostMapping("/user/register")
    public JsonResponse<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return JsonResponse.success();

    }

    @PostMapping("/user/login")
    public JsonResponse<String> login(@RequestBody User user) {
        String token = userService.login(user);
        return new JsonResponse<>(token);

    }

    @GetMapping("users")
    public List<Map<String, Object>> userList() {
        return jdbcTemplate.queryForList("SELECT * FROM user");
    }

}
