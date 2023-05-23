package com.nhnacademy.spring.jpa_security.service.github;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface GithubLoginService {
    String login(String code) throws JsonProcessingException;
}
