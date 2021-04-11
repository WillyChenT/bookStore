package dev.ming.bookStore.utils;

import dev.ming.bookStore.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * JWT工具類
 * 1.生成的token,是可以透過base64解密出訊息
 * 2.base64解密訊息後，修改再編碼，則會解密失敗
 * 3.無法作廢發布的token除非改密鑰
 */
public class JWTUtils {
    /**
     * 過期時間，一周
     */
    private static final long EXPIRE = 60000 * 60 * 24 * 7;

    /**
     * 加密金鑰
     */
    private  static final String SECRET = "KeyForMing";

    /**
     * Token前綴
     */
    private static final String TOKEN_PREFIX = "BookStore";

    /**
     * subject
     */
    private static final String SUBJECT = "Study123";

    public static String genJsonWebToken(User user){

        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("head_img", user.getHeadImg())
                .claim("id", user.getId())
                .claim("name", user.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE))
                .signWith(SignatureAlgorithm.HS256,SECRET).compact();

        return TOKEN_PREFIX +token;

    }

    public static Claims checkJWT(String token) {
        try{
            Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
            return claims;
        }catch(Exception e){
            return null;
        }
    }
}
