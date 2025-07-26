package bcy.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import bcy.dao.JsonResponse;
import bcy.dao.Reply;
import bcy.service.ReplyService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin(origins = "*")
public class ReplyApi {

    @Autowired
    private ReplyService replyService;

    @GetMapping("/reply/{id}")
    public JsonResponse<Reply> getCurrentReply(@PathVariable String id) {
        Reply reply = replyService.getReplyById(Long.valueOf(id));
        return new JsonResponse<>(reply);
    }

    @PostMapping("/reply")
    public JsonResponse<String> addReply(@RequestBody Reply reply) {
        replyService.addReply(reply);
        return JsonResponse.success();
    }

    @GetMapping("/replys")
    public JsonResponse<List<Reply>> getReplys(String pid, String cid, String page, String size) {
        if (pid == null) {
            pid = "0";
        }
        if (cid == null) {
            cid = "0";
        }

        List<Reply> list = replyService.getReplys(Long.valueOf(pid), Long.valueOf(cid), Long.valueOf(page),
                Long.valueOf(size));

        return new JsonResponse<>(list);
    }

}
