package bcy.service;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.auth0.jwt.interfaces.DecodedJWT;

import bcy.service.util.TokenUtil;

@Component
public class UserSupport {
    public DecodedJWT getTokenUser() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        String token = requestAttributes.getRequest().getHeader("token");
        return TokenUtil.parseToken(token);
    }

    public Long getCurrentUserId() {
        DecodedJWT token = this.getTokenUser();
        Long uid = Long.valueOf(token.getClaim("uid").toString());
        return uid;
    }

    public Integer getCurrentUserLevel() {
        DecodedJWT token = this.getTokenUser();
        Integer level = Integer.valueOf(token.getClaim("level").toString());
        return level;
    }
}
