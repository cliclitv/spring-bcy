package bcy.service.util;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import bcy.dao.JsonResponse;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ConditionException extends RuntimeException {
    private Integer code;

    public ConditionException(Integer code, String name) {
        super(name);
        this.code = code;

    }

    public ConditionException(String name) {
        super(name);
        code = 500;
    }

    public ConditionException() {
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResponse<String> CommonException(HttpServletRequest req, Exception e) {
        String err = e.getMessage();
        if (e instanceof ConditionException) {
            Integer code = ((ConditionException) e).getCode();
            return new JsonResponse<>(code, err);
        } else {
            System.out.println(e);
            return new JsonResponse<>(500, err);
        }
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
