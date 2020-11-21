package com.jiu.system.utils;

import io.jsonwebtoken.*;

import java.util.Date;

/**
 * Jwt工具类型
 *
 * @author wang.zhang
 */
public class JwtUtils {
    /**
     * 过期时间7天
     */
    private final static int EXPIRE_TIME = 604800000;

    /**
     * 签名使用的KEY
     */
    private final static String KEY = "!&SUcl%cCURy";

    /**
     * 签名
     *
     * @param claims 签名内容
     * @return Token字符串
     */
    public static String sign(Claims claims) {
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        JwtBuilder builder = Jwts.builder();
        builder.setClaims(claims);
        builder.setIssuedAt(new Date());
        builder.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME));
        builder.signWith(algorithm, KEY);
        //进行AES加密
        //String token = AESUtils.AESEncode(BaseConstants.AES_ENCODE_KEY, builder.compact());
        return builder.compact();
    }

    /**
     * 解析JWT
     *
     * @param token 认证字符串
     * @return 返回解析结果
     */
    public static Claims parse(String token) {
        JwtParser parser = Jwts.parser();
        parser.setSigningKey(KEY);
        //先进行AES解密
        //String tokenString = AESUtils.AESDncode(BaseConstants.AES_ENCODE_KEY, token);
        Jws<Claims> claimsJws = parser.parseClaimsJws(token);
        return claimsJws.getBody();
    }
}
