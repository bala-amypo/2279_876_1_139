// // package com.example.demo.config;

// // import com.example.demo.security.JwtAuthenticationFilter;
// // import com.example.demo.security.JwtTokenProvider;
// // import org.springframework.context.annotation.Bean;
// // import org.springframework.context.annotation.Configuration;
// // import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// // import org.springframework.security.web.SecurityFilterChain;
// // import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// // @Configuration
// // public class SecurityConfig {

// //     @Bean
// //     public SecurityFilterChain securityFilterChain(
// //             HttpSecurity http,
// //             JwtTokenProvider jwtTokenProvider
// //     ) throws Exception {

// //         http
// //             .csrf(csrf -> csrf.disable())
// //             .authorizeHttpRequests(auth -> auth
// //                 .requestMatchers(
// //                         "/auth/**",
// //                         "/swagger-ui/**",
// //                         "/v3/api-docs/**"
// //                 ).permitAll()
// //                 .anyRequest().authenticated()
// //             )
// //             .addFilterBefore(
// //                 new JwtAuthenticationFilter(jwtTokenProvider),
// //                 UsernamePasswordAuthenticationFilter.class
// //             );

// //         return http.build();
// //     }
// // }


// // package com.example.demo.config;

// // import org.springframework.context.annotation.Bean;
// // import org.springframework.context.annotation.Configuration;
// // import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// // import org.springframework.security.web.SecurityFilterChain;

// // @Configuration
// // public class SecurityConfig {

// //     @Bean
// //     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

// //         http
// //             .csrf(csrf -> csrf.disable())
// //             .authorizeHttpRequests(auth -> auth

// //                 // ✅ ALLOW SWAGGER
// //                 .requestMatchers(
// //                         "/swagger-ui.html",
// //                         "/swagger-ui/**",
// //                         "/v3/api-docs/**",
// //                         "/v3/api-docs",
// //                         "/swagger-resources/**",
// //                         "/webjars/**"
// //                 ).permitAll()

// //                 // auth controller allowed
// //                 .requestMatchers("/auth/**").permitAll()

// //                 // everything else secured
// //                 .anyRequest().authenticated()
// //             );

// //         return http.build();
// //     }
// // }






// package com.example.demo.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// public class SecurityConfig {

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//         http
//             // Disable CSRF (JWT REST API)
//             .csrf(csrf -> csrf.disable())

//             // Disable default login mechanisms
//             .formLogin(form -> form.disable())
//             .httpBasic(basic -> basic.disable())

//             .authorizeHttpRequests(auth -> auth

//                 // 🔓 ROOT
//                 .requestMatchers("/").permitAll()

//                 // 🔓 AUTH APIs
//                 .requestMatchers("/auth/**").permitAll()

//                 // 🔓 SWAGGER
//                 .requestMatchers(
//                         "/swagger-ui.html",
//                         "/swagger-ui/**",
//                         "/v3/api-docs/**",
//                         "/swagger-resources/**",
//                         "/webjars/**"
//                 ).permitAll()

//                 // ✅ USER can access ALL APIs
//                 .requestMatchers("/api/**").hasRole("USER")

//                 // 🔒 Everything else
//                 .anyRequest().authenticated()
//             );

//         return http.build();
//     }
// }


package com.example.demo.config; //recent 

import com.example.demo.security.JwtAuthenticationFilter;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable())

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/").permitAll()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers(
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/v3/api-docs/**"
                ).permitAll()

                // ✅ USER CAN ACCESS ALL APIs
                .requestMatchers("/api/**").hasRole("USER")

                .anyRequest().authenticated()
            )

            // 🔥 REGISTER JWT FILTER HERE
            .addFilterBefore(
                    new JwtAuthenticationFilter(jwtTokenProvider),
                    UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}
