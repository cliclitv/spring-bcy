package bcy.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import bcy.dao.JsonResponse;
import bcy.dao.Term;
import bcy.service.TermService;

@RestController
public class TermApi {
    @Autowired
    private TermService tagService;

    @GetMapping("/terms")
    public JsonResponse<List<Term>> getTags(String pid) {
        List<Term> list = tagService.getTags(Long.valueOf(pid));
        return new JsonResponse<>(list);
    }

    @PostMapping("/term")
    public JsonResponse<String> addTag(@RequestBody Term tag) {
        tagService.addTag(tag);
        return JsonResponse.success();

    }
}
