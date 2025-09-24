//package com.ecomm.controller;
//
//import com.ecomm.config.security.JwtAuthFilter;
//import com.ecomm.entity.Role;
//import com.ecomm.entity.User;
//import com.ecomm.repository.RoleRepository;
//import com.ecomm.repository.UserRepository;
//import com.ecomm.userservice.UserServiceApplication;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Import;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Map;
//import java.util.UUID;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest(classes = UserServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
//@TestPropertySource(properties = {
//        // keep tests offline
//        "eureka.client.enabled=false",
//        "spring.cloud.discovery.enabled=false",
//        // get a clean schema
//        "spring.jpa.hibernate.ddl-auto=create-drop",
//
//        // === JWT test config ===
//        // 32-byte key (base64). Value: "0123456789abcdef0123456789abcdef"
//        "app.security.jwt.secret=MDEyMzQ1Njc4OWFiY2RlZjAxMjM0NTY3ODlhYmNkZWY=",
//        "app.security.jwt.access-expiration-ms=900000",
//        "app.security.jwt.refresh-expiration-ms=604800000",
//
//        // auto-verify on register so /me works right away
//        "app.security.flags.auto-verify-on-register=true"
//})
//@Import(UserControllerTest.TestSecurityConfig.class)
//class UserControllerTest {
//
//    @Autowired MockMvc mockMvc;
//    @Autowired ObjectMapper objectMapper;
//
//    @Autowired UserRepository userRepository;
//    @Autowired RoleRepository roleRepository;
//    @Autowired PasswordEncoder passwordEncoder;
//
//    @Test
//    void shouldRegisterNewUserAndAllowAccessToMeUsingReturnedToken() throws Exception {
//        String email = "u" + UUID.randomUUID().toString().replace("-", "") + "@example.com";
//        String password = "Bob@12345";
//
//        var reg = Map.of("username", "bob", "email", email, "password", password);
//        var regRes = mockMvc.perform(post("/api/users/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(reg)))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        JsonNode regBody = objectMapper.readTree(regRes.getResponse().getContentAsString());
//        String access = regBody.get("accessToken").asText();
//        assertThat(access).isNotBlank();
//
//        mockMvc.perform(get("/api/users/me")
//                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + access))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void shouldLoginWithPreseededUserAndAllowAccessToMe() throws Exception {
//        String email = "u" + UUID.randomUUID().toString().replace("-", "") + "@example.com";
//        String rawPassword = "Bob@12345";
//
//        Role roleUser = roleRepository.findByName("ROLE_USER")
//                .orElseGet(() -> roleRepository.save(Role.builder()
//                        .name("ROLE_USER")
//                        .description("Default user role")
//                        .build()));
//
//        User u = new User();
//        u.setEmail(email);
//        u.setUsername("bob");
//        u.setPasswordHash(passwordEncoder.encode(rawPassword));
//        u.setIsActive(true);
//        u.setIsEmailVerified(true);
//        u.setTokenVersion(0);
//        u.getRoles().add(roleUser);
//        userRepository.save(u);
//
//        var login = Map.of("email", email, "password", rawPassword);
//        var loginRes = mockMvc.perform(post("/api/users/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(login)))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        JsonNode loginBody = objectMapper.readTree(loginRes.getResponse().getContentAsString());
//        String access = loginBody.get("accessToken").asText();
//        assertThat(access).isNotBlank();
//
//        mockMvc.perform(get("/api/users/me")
//                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + access))
//                .andExpect(status().isOk());
//    }
//
//    /** Test-only chain: disable CSRF; allow register/login; keep JWT for /me */
//    @TestConfiguration
//    static class TestSecurityConfig {
//        @Bean
//        @Order(SecurityProperties.BASIC_AUTH_ORDER)
//        SecurityFilterChain testFilterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
//            http
//                    .csrf(csrf -> csrf.disable())
//                    .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                    .authorizeHttpRequests(auth -> auth
//                            .requestMatchers("/api/users/register",
//                                    "/api/users/login",
//                                    "/actuator/**",
//                                    "/v3/api-docs/**",
//                                    "/swagger-ui/**").permitAll()
//                            .anyRequest().authenticated()
//                    )
//                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//            return http.build();
//        }
//    }
//}
