package bcy.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import bcy.dao.JsonResponse;
import bcy.dao.Comment;
import bcy.service.CommentService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class CommentApi {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comment/{id}")
    public JsonResponse<Comment> getCurrentComment(@PathVariable String id) {
        Comment comment = commentService.getCommentById(Long.valueOf(id));
        return new JsonResponse<>(comment);
    }

    @PostMapping("/comment")
    public JsonResponse<String> addComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
        return JsonResponse.success();
    }

    @GetMapping("/comments")
    public JsonResponse<List<Comment>> getComments(String pid, String cid, String page, String size) {
        if (pid == null) {
            pid = "0";
        }
        if (cid == null) {
            cid = "0";
        }

        List<Comment> list = commentService.getComments(Long.valueOf(pid), Long.valueOf(cid), Long.valueOf(page),
                Long.valueOf(size));

        return new JsonResponse<>(list);
    }

}
