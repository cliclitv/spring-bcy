package bcy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import bcy.dao.Follow;
import bcy.dao.JsonResponse;
import bcy.service.FollowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class FollowApi {
    @Autowired
    private FollowService followService;

    @PostMapping("/follow")
    public JsonResponse<String> replaceFollow(@RequestBody Follow follow) {
        followService.replaceFollow(follow);
        return JsonResponse.success();
    }

    @GetMapping("/follow")
    public String getFollowStatus(@RequestParam String param) {
        return new String();
    }
    
}
