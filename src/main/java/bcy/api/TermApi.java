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
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class TermApi {
    @Autowired
    private TermService termService;

    @GetMapping("/terms")
    public JsonResponse<List<Term>> getTerms(String pid, String uid) {
        if (uid == null) {
            uid = "0";
        }
        if (pid == null) {
            pid = "0";
        }
        List<Term> list = termService.getTerms(Long.valueOf(pid));
        return new JsonResponse<>(list);
    }

    @PostMapping("/term")
    public JsonResponse<String> addTerm(@RequestBody Term term) {
        termService.addTerm(term);
        return JsonResponse.success();
    }

    @PutMapping("/term")
    public JsonResponse<String> updateTerm(@RequestBody Term term) {
        termService.addTerm(term);
        return JsonResponse.success();
    }
}
