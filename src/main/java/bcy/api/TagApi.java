package bcy.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import bcy.dao.JsonResponse;
import bcy.dao.Tag;
import bcy.service.TagService;

@RestController
public class TagApi {
    @Autowired
    private TagService tagService;

    @GetMapping("/collections")
    public JsonResponse<List<Tag>> getTags(String pid) {
        List<Tag> list = tagService.getTags(Long.valueOf(pid));
        return new JsonResponse<>(list);
    }

    @PostMapping("/collection")
    public JsonResponse<String> addTag(@RequestBody Tag tag) {
        tagService.addTag(tag);
        return JsonResponse.success();

    }
}
