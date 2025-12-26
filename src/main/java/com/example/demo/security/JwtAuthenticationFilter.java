// // package com.example.demo.security;

// // import jakarta.servlet.*;
// // import jakarta.servlet.http.HttpServletRequest;
// // import java.io.IOException;

// // public class JwtAuthenticationFilter implements Filter {

// //     private final JwtTokenProvider jwtTokenProvider;

// //     public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
// //         this.jwtTokenProvider = jwtTokenProvider;
// //     }

// //     @Override
// //     public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
// //             throws IOException, ServletException {

// //         HttpServletRequest request = (HttpServletRequest) req;
// //         String auth = request.getHeader("Authorization");

// //         if (auth != null && auth.startsWith("Bearer ")) {
// //             String token = auth.substring(7);
// //             jwtTokenProvider.validateToken(token);
// //         }

// //         chain.doFilter(req, res);
// //     }
// // }





// package com.example.demo.security;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;
// import java.util.List;
// import java.util.Set;
// import java.util.stream.Collectors;

// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     private final JwtTokenProvider jwtTokenProvider;

//     public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
//         this.jwtTokenProvider = jwtTokenProvider;
//     }

//     @Override
//     protected void doFilterInternal(
//             HttpServletRequest request,
//             HttpServletResponse response,
//             FilterChain filterChain
//     ) throws ServletException, IOException {

//         String auth = request.getHeader("Authorization");

//         if (auth != null && auth.startsWith("Bearer ")) {

//             String token = auth.substring(7);

//             if (jwtTokenProvider.validateToken(token)) {

//                 String email = jwtTokenProvider.getUsernameFromToken(token);
//                 Set<String> roles = jwtTokenProvider.getRoles(token);

//                 // 🔥 ROLES → GRANTED AUTHORITIES
//                 List<SimpleGrantedAuthority> authorities =
//                         roles.stream()
//                                 .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//                                 .collect(Collectors.toList());

//                 UsernamePasswordAuthenticationToken authentication =
//                         new UsernamePasswordAuthenticationToken(
//                                 email,
//                                 null,
//                                 authorities
//                         );

//                 authentication.setDetails(
//                         new WebAuthenticationDetailsSource()
//                                 .buildDetails(request)
//                 );

//                 SecurityContextHolder.getContext()
//                         .setAuthentication(authentication);
//             }
//         }

//         filterChain.doFilter(request, response);
//     }
// }
package com.example.demo.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            if (jwtTokenProvider.validateToken(token)) {

                Claims claims = jwtTokenProvider.getClaims(token);

                List<String> roles = jwtTokenProvider.getRoles(claims);

                var authorities = roles.stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                        .collect(Collectors.toList());

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                claims.getSubject(),
                                null,
                                authorities
                        );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
