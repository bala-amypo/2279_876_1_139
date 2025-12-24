// // package com.example.demo.config;

// // import io.swagger.v3.oas.models.OpenAPI;
// // import io.swagger.v3.oas.models.Components;
// // import io.swagger.v3.oas.models.info.Info;
// // import io.swagger.v3.oas.models.security.SecurityRequirement;
// // import io.swagger.v3.oas.models.security.SecurityScheme;
// // import io.swagger.v3.oas.models.servers.Server;
// // import org.springframework.context.annotation.Bean;
// // import org.springframework.context.annotation.Configuration;

// // import java.util.List;

// // @Configuration
// // public class OpenApiConfig {

// //     @Bean
// //     public OpenAPI customOpenAPI() {

// //         return new OpenAPI()
// //                 .info(new Info()
// //                         .title("University Course Transfer Validator API")
// //                         .version("1.0"))
// //                 .servers(List.of(
// //                         new Server().url("")
// //                 ))
// //                 .addSecurityItem(
// //                         new SecurityRequirement().addList("bearerAuth")
// //                 )
// //                 .components(
// //                         new Components().addSecuritySchemes(
// //                                 "bearerAuth",
// //                                 new SecurityScheme()
// //                                         .type(SecurityScheme.Type.HTTP)
// //                                         .scheme("bearer")
// //                                         .bearerFormat("JWT")
// //                         )
// //                 );
// //     }
// // }
// package com.example.demo.config;

// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.info.Info;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class OpenApiConfig {

//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//                 .info(new Info()
//                         .title("University Course Transfer Validator API")
//                         .version("1.0")
//                         .description("API documentation"));
//     }
// }


package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    // ✅ REQUIRED by UserAccountServiceImpl
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ✅ REQUIRED by AuthController (DUMMY for assignment/tests)
    @Bean
    public AuthenticationManager authenticationManager() {
        return authentication -> authentication;
    }

    // ✅ Minimal security (no real auth)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            );

        return http.build();
    }
}