package com.nhnacademy.spring.jpa_security.service.github;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.spring.jpa_security.exception.InvalidAccessCodeException;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:github.properties")
public class GithubLoginServiceImpl implements GithubLoginService {
    @Value("${github.client_id}")
    private String clientId;
    @Value("${github.client_secret}")
    private String clientSecret;

    private final String GITHUB_EMAIL_ADDRESS_API = "https://api.github.com/user/emails";
    private final String GITHUB_ACCESS_TOKEN_API = "https://github.com/login/oauth/access_token";

    @Override
    public String login(String code) throws JsonProcessingException {
        String accessToken;

        if (Objects.isNull(accessToken = getGithubAccessToken(code))) {
            throw new InvalidAccessCodeException(code);
        }

        return getGithubEmailAddress(accessToken);
    }

    private String getGithubEmailAddress(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity githubRequest = new HttpEntity<>(headers);

        RestTemplate rt = new RestTemplate();
        ResponseEntity<List> githubResponse = rt.exchange(
                GITHUB_EMAIL_ADDRESS_API,
                HttpMethod.GET,
                githubRequest,
                List.class
        );
        ObjectMapper mapper = new ObjectMapper();

        String email = ((List<UserEmail>) githubResponse.getBody().stream()
                .map(o -> mapper.convertValue(o, UserEmail.class))
                .collect(Collectors.toList()))
                .get(0).getEmail();

        return email;
    }

    private String getGithubAccessToken(String code) throws JsonProcessingException {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("code", code);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> githubRequest = new HttpEntity<>(params, headers);

        RestTemplate rt = new RestTemplate();
        ResponseEntity<AccessToken> githubResponse = rt.exchange(
                GITHUB_ACCESS_TOKEN_API,
                HttpMethod.POST,
                githubRequest,
                AccessToken.class
        );

        return githubResponse.getBody().getAccess_token();
    }
    @Getter
    @ToString
    static class AccessToken {
        private String access_token;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    static class UserEmail {
        private String email;
        private String primary;
        private String verified;
        private String visibility;
    }
}
