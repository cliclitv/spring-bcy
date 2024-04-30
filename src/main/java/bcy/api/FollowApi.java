package bcy.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowApi {
    @PostMapping("follow")
    public String replaceFollow(@RequestBody String entity) {
        
        return entity;
    }
}
