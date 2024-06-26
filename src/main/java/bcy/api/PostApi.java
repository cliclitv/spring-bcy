package bcy.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import bcy.dao.JsonResponse;
import bcy.dao.Post;
import bcy.service.PostService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin(origins = "*")
public class PostApi {
    @Autowired
    private PostService postService;

    @GetMapping("/post/{id}")
    public JsonResponse<Post> getCurrentPost(@PathVariable String id) {
        Post post = postService.getPostById(Long.valueOf(id));
        return new JsonResponse<>(post);
    }

    @GetMapping("/pv/{pid}")
    public JsonResponse<Long> replacePv(@PathVariable String pid) {
        Long pv = postService.replacePv(Long.valueOf(pid));
        return new JsonResponse<>(pv);
    }

    @PostMapping("/post")
    public JsonResponse<String> addPost(@RequestBody Post post) {
        postService.addPost(post);
        return JsonResponse.success();

    }

    @GetMapping("/posts")
    public JsonResponse<List<Post>> getPosts(String tag, String uid, String pid,
            String search, String page, String size, String fid) {
        if (uid == null) {
            uid = "0";
        }
        if (pid == null) {
            pid = "0";
        }
        if (page == null) {
            page = "0";
        }
        if (fid == null) {
            fid = "0";
        }
        if (size == null) {
            size = "0";
        }
        List<Post> list = postService.getPosts(tag, Long.valueOf(uid), Long.valueOf(pid), search,
                Long.valueOf(page), Long.valueOf(size), Long.valueOf(fid));
        return new JsonResponse<>(list);
    }

}
