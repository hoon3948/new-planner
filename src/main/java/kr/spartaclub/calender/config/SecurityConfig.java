package kr.spartaclub.calender.config;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        // 1. API 개발에 불필요한 기본 설정 비활성화
//        http
//                .csrf(csrf -> csrf.disable())
//                .formLogin(form -> form.disable())
//                .httpBasic(basic -> basic.disable());
//
//        // 2. 경로별 접근 권한 설정
//        http.authorizeHttpRequests(auth -> auth
//                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
//                .requestMatchers(
//                        "/",
//                        "/signup",
//                        "/api/auth/**",     // 👈 여기가 열려야 회원가입 가능!
//                        "/error",
//                        "/css/**", "/js/**", "/images/**", "/favicon.ico"
//                ).permitAll()
//                .anyRequest().authenticated() // 나머지는 로그인해야 접근 가능
//        );
//
//        return http.build();
//    }
//}