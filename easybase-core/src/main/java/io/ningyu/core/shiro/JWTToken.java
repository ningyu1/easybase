package io.ningyu.core.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @ClassName: JWTToken
 * @Description:
 */
public class JWTToken implements AuthenticationToken {

    private static final long serialVersionUID = -2819620437297382200L;

    /**
     * 密钥
     */
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
