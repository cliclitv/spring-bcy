package bcy.api;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import bcy.service.util.TokenUtil;

@Component
public class UserSupport {
    public Long getCurrentUserId() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        String token = requestAttributes.getRequest().getHeader("token");
        Long uid = TokenUtil.parseToken(token);
        return uid;
    }
}
