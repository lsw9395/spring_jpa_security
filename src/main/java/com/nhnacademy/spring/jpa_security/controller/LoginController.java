package com.nhnacademy.spring.jpa_security.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhnacademy.spring.jpa_security.entity.Resident;
import com.nhnacademy.spring.jpa_security.repository.resident.ResidentRepository;
import com.nhnacademy.spring.jpa_security.service.github.GithubLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Objects;

@Controller
@PropertySource("classpath:github.properties")
@RequiredArgsConstructor
public class LoginController {
    private final ResidentRepository residentRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final GithubLoginService githubLoginService;
    @Value("${github.client_id}")
    private String clientId;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("clientId", clientId);
        model.addAttribute("scope", "user:email");

        return "login";
    }

    @GetMapping("/login/oauth2/code/github")
    public String callbackFromGithub(String code,
                                     HttpServletRequest request) throws JsonProcessingException {
        String emailAddress = githubLoginService.login(code);

        request.setAttribute("email", emailAddress);

        return "forward:/loginSuccess";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccessHandler(HttpServletRequest request) {
        String emailAddress = (String) request.getAttribute("email");


        Resident resident = residentRepository.findByEmail(emailAddress).orElse(null);

        if(Objects.isNull(resident)) {
            return "redirect:/login?error=email";
        }

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        resident.getId(),
                        resident.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))));

        HttpSession session = request.getSession(false);

        redisTemplate.opsForHash().put(session.getId(), "username", resident.getId());
        redisTemplate.opsForHash().put(session.getId(), "authority", "ROLE_USER");

        session.setAttribute("username", resident.getId());
        session.setAttribute("authority", "ROLE_USER");

        return "home";
    }
}
