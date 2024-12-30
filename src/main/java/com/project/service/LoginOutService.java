package com.project.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.project.dto.User;

import org.springframework.beans.factory.annotation.Value;

@Service
public class LoginOutService {

    @Value("${naver.client.id}")
    private String clientId;

    @Value("${naver.client.secret}")
    private String clientSecret;

    private static final String NAVER_API_URL = "https://openapi.naver.com/v1/nid/me";

    // 네이버 로그인 정보 가져오기
    public User getUserFromNaver(String code, String state) {
        // 네이버 로그인 토큰 요청 URL
        String tokenUrl = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&client_id=" 
                          + clientId + "&client_secret=" + clientSecret + "&code=" + code + "&state=" + state;

        RestTemplate restTemplate = new RestTemplate();
        
        // 토큰 요청
        ResponseEntity<String> tokenResponse = restTemplate.exchange(tokenUrl, HttpMethod.GET, null, String.class);
        String accessToken = parseAccessToken(tokenResponse.getBody());

        // 액세스 토큰을 사용해 사용자 정보 요청
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> userResponse = restTemplate.exchange(NAVER_API_URL, HttpMethod.GET, entity, String.class);

        // Parse user info and create a User object
        User user = parseUserInfo(userResponse.getBody());
        
        return user;
    }

    // 액세스 토큰 파싱
    private String parseAccessToken(String response) {
        // You need to parse the access token from the response. 
        // For simplicity, let's assume response is JSON and use a simple approach
        // (you may want to use a library like Jackson or Gson to parse JSON properly).
        int tokenStart = response.indexOf("access_token\":\"") + 15;
        int tokenEnd = response.indexOf("\"", tokenStart);
        return response.substring(tokenStart, tokenEnd);
    }

    // 사용자 정보 파싱 (예시로 이름만 파싱)
    private User parseUserInfo(String response) {
        // Parse the user information from the response.
        int idStart = response.indexOf("id\":\"") + 7;
        int idEnd = response.indexOf("\"", idStart);
        String id = response.substring(idStart, idEnd);

        // Create a User object and set values from the response
        User user = new User();
        user.setId(id);

        return user;
    }
}
