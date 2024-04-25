package bcy.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import bcy.dao.JsonResponse;
import bcy.dao.Post;
import bcy.service.PostService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class PostApi {
    @Autowired
    private PostService postService;

    @GetMapping("/post/{id}")
    public JsonResponse<Post> getCurrentPost(@PathVariable String id) {
        Post post = postService.getPostById(Long.valueOf(id));
        return new JsonResponse<>(post);
    }

    @PostMapping("/post")
    public JsonResponse<String> addPost(@RequestBody Post post) {
        postService.addPost(post);
        return JsonResponse.success();

    }

    @PutMapping("/post/{id}")
    public JsonResponse<String> putMethodName(@PathVariable String id, @RequestBody Post post) {
        post.setId(Long.valueOf(id));

        postService.updatePost(post);

        return JsonResponse.success();
    }

    @GetMapping("/posts")
    public JsonResponse<List<Post>> getPosts(String status, String sort, String tag, Long uid, Long pid,
            String search, String page, String size) {
        List<Post> list = postService.getPosts(status, sort, tag, Long.valueOf(uid), Long.valueOf(pid), search, Long.valueOf(page), Long.valueOf(size));
        return new JsonResponse<>(list);
    }

}
