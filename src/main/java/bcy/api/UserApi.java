package bcy.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

import bcy.dao.JsonResponse;
import bcy.dao.User;
import bcy.service.UserService;
import jakarta.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@CrossOrigin(origins = "*")
public class UserApi {
    @Autowired
    private UserService userService;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/user/{id}")
    public Map<String, Object> getUser(Long id) {
        return userService.getUserById(id);
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
