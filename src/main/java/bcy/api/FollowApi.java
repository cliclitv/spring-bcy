package bcy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import bcy.dao.Follow;
import bcy.dao.JsonResponse;
import bcy.service.FollowService;
import org.springframework.web.bind.annotation.GetMapping;

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
    public JsonResponse<String> getFollowStatus(String uid, String tid) {
        if(uid==null){
            uid="0";
        }
        if(tid==null){
            tid="0";
        }
        String status = followService.getFollowStatus(Long.valueOf(uid), Long.valueOf(tid));
        return new JsonResponse<>(status);
    }

    @PostMapping("/collect")
    public JsonResponse<String> replaceCollect(@RequestBody Follow follow) {
        followService.replaceFollow(follow);
        return JsonResponse.success();
    }

    @GetMapping("/collect")
    public JsonResponse<String> getCollectStatus(String uid, String tid) {
        if(uid==null){
            uid="0";
        }
        if(tid==null){
            tid="0";
        }
        String status = followService.getCollectStatus(Long.valueOf(uid), Long.valueOf(tid));
        return new JsonResponse<>(status);
    }

}
