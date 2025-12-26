// // package com.example.demo.security;

// // import io.jsonwebtoken.Claims;
// // import io.jsonwebtoken.Jwts;
// // import io.jsonwebtoken.SignatureAlgorithm;
// // import io.jsonwebtoken.security.Keys;
// // import org.springframework.stereotype.Component;

// // import java.security.Key;
// // import java.util.Collection;
// // import java.util.Date;
// // import java.util.HashSet;
// // import java.util.Set;

// // @Component
// // public class JwtTokenProvider {

// //     private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
// //     private final long validityInMs = 60 * 60 * 1000;

// //     public String createToken(Long userId, String email, Set<String> roles) {

// //         Claims claims = Jwts.claims().setSubject(email);
// //         claims.put("userId", userId);
// //         claims.put("roles", roles);

// //         Date now = new Date();
// //         Date expiry = new Date(now.getTime() + validityInMs);

// //         return Jwts.builder()
// //                 .setClaims(claims)
// //                 .setIssuedAt(now)
// //                 .setExpiration(expiry)
// //                 .signWith(key)
// //                 .compact();
// //     }

// //     public boolean validateToken(String token) {
// //         try {
// //             Jwts.parserBuilder()
// //                     .setSigningKey(key)
// //                     .build()
// //                     .parseClaimsJws(token);
// //             return true;
// //         } catch (Exception e) {
// //             return false;
// //         }
// //     }

// //     public String getEmail(String token) {
// //         return getClaims(token).getSubject();
// //     }

// //     public Long getUserId(String token) {
// //         return getClaims(token).get("userId", Long.class);
// //     }

    
// //     public Set<String> getRoles(String token) {
// //         Object roles = getClaims(token).get("roles");
// //         Set<String> result = new HashSet<>();

// //         if (roles instanceof Collection<?>) {
// //             for (Object r : (Collection<?>) roles) {
// //                 result.add(String.valueOf(r));
// //             }
// //         }

// //         return result;
// //     }

// //     private Claims getClaims(String token) {
// //         return Jwts.parserBuilder()
// //                 .setSigningKey(key)
// //                 .build()
// //                 .parseClaimsJws(token)
// //                 .getBody();
// //     }
// // }





// package com.example.demo.security;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.stereotype.Component;

// import java.nio.charset.StandardCharsets;
// import java.security.Key;
// import java.util.Collection;
// import java.util.Date;
// import java.util.HashSet;
// import java.util.Set;

// @Component
// public class JwtTokenProvider {

//     // fixed secret (same across restarts)
//     private static final String SECRET_KEY =
//             "mysecretkeymysecretkeymysecretkey123";

//     private static final long VALIDITY_IN_MS = 60 * 60 * 1000; // 1 hour

//     private Key getSigningKey() {
//         return Keys.hmacShaKeyFor(
//                 SECRET_KEY.getBytes(StandardCharsets.UTF_8)
//         );
//     }

//     // ---------- CREATE TOKEN ----------
//     public String createToken(Long userId, String email, Set<String> roles) {

//         Claims claims = Jwts.claims().setSubject(email);
//         claims.put("userId", userId);
//         claims.put("roles", roles);

//         Date now = new Date();
//         Date expiry = new Date(now.getTime() + VALIDITY_IN_MS);

//         return Jwts.builder()
//                 .setClaims(claims)
//                 .setIssuedAt(now)
//                 .setExpiration(expiry)
//                 .signWith(getSigningKey(), SignatureAlgorithm.HS256)
//                 .compact();
//     }

//     // ---------- VALIDATE ----------
//     public boolean validateToken(String token) {
//         try {
//             Jwts.parserBuilder()
//                     .setSigningKey(getSigningKey())
//                     .build()
//                     .parseClaimsJws(token);
//             return true;
//         } catch (Exception e) {
//             return false;
//         }
//     }

//     // ---------- CLAIM READERS ----------
//     public String getUsernameFromToken(String token) {
//         return getClaims(token).getSubject();
//     }

//     // 🔥 REQUIRED FOR TESTS (FullProjectTest.java)
//     public String getEmail(String token) {
//         return getClaims(token).getSubject();
//     }

//     public Long getUserId(String token) {
//         return getClaims(token).get("userId", Long.class);
//     }

//     public Set<String> getRoles(String token) {
//         Object roles = getClaims(token).get("roles");
//         Set<String> result = new HashSet<>();

//         if (roles instanceof Collection<?>) {
//             for (Object r : (Collection<?>) roles) {
//                 result.add(String.valueOf(r));
//             }
//         }
//         return result;
//     }

//     private Claims getClaims(String token) {
//         return Jwts.parserBuilder()
//                 .setSigningKey(getSigningKey())
//                 .build()
//                 .parseClaimsJws(token)
//                 .getBody();
//     }
// }
package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public class JwtTokenProvider {

    private final String SECRET_KEY = "my-secret-key"; 
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    // ✅ CREATE TOKEN (FIXES COMPILATION ERROR)
    public String createToken(Long userId, String email, Set<String> roles) {

        Claims claims = Jwts.claims().setSubject(email);
        claims.put("userId", userId);
        claims.put("roles", roles);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // ✅ VALIDATE TOKEN
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ GET CLAIMS
    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    // ✅ GET ROLES
    public List<String> getRoles(Claims claims) {
        return claims.get("roles", List.class);
    }
}
