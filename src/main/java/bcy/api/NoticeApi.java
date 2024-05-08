package bcy.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import bcy.dao.JsonResponse;
import bcy.dao.Notice;
import bcy.service.NoticeService;

@RestController
public class NoticeApi {
    @Autowired
    private NoticeService noticeService;

    @GetMapping("/notices")
    public JsonResponse<List<Notice>> getPosts(String tid) {
        List<Notice> list = noticeService.getNotices(Long.valueOf(tid));
        return new JsonResponse<>(list);
    }

    @PostMapping("/notice")
    public JsonResponse<String> addNotice(@RequestBody Notice notice) {
        noticeService.addNotice(notice);
        return JsonResponse.success();

    }
}
