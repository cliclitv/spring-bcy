package bcy.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import bcy.dao.JsonResponse;
import bcy.dao.Term;
import bcy.service.TermService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin(origins = "*")
public class TermApi {
    @Autowired
    private TermService termService;

    @GetMapping("/terms")
    public JsonResponse<List<Term>> getTerms(String cat, String author, String uid) {
        if (uid == null) {
            uid = "0";
        }
        List<Term> list = termService.getTerms(cat, author, Long.valueOf(uid));
        return new JsonResponse<>(list);
    }

    @PostMapping("/term")
    public JsonResponse<String> addTerm(@RequestBody Term term) {
        termService.addTerm(term);
        return JsonResponse.success();
    }

}
