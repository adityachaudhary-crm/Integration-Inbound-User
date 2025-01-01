package com.example;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.client.RestTemplate;

@Controller
public class SalesforceController {

    private final RestTemplate restTemplate;
    private final OAuth2AuthorizedClientService authorizedClientService;

    public SalesforceController(RestTemplateBuilder restTemplateBuilder, OAuth2AuthorizedClientService authorizedClientService) {
        this.restTemplate = restTemplateBuilder.build();
        this.authorizedClientService = authorizedClientService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/oauth2/authorization/salesforce";
    }

    @GetMapping("/api/salesforce")
    public ResponseEntity<?> getSalesforceData(OAuth2AuthenticationToken authentication) {
        OAuth2AuthorizedClient client = getAuthorizedClient(authentication);
        if (client == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String accessToken = client.getAccessToken().getTokenValue();

        // Use the access token to fetch data from Salesforce
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String resourceUrl = "https://adityachaudharycrm-dev-ed.develop.my.salesforce.com/services/data/v62.0/sobjects/Account/001aj00000nCJajAAG";
        ResponseEntity<String> response = restTemplate.exchange(resourceUrl, HttpMethod.GET, entity, String.class);

        return ResponseEntity.ok(response.getBody());
    }

    private OAuth2AuthorizedClient getAuthorizedClient(OAuth2AuthenticationToken authentication) {
        return authorizedClientService.loadAuthorizedClient(
                authentication.getAuthorizedClientRegistrationId(),
                authentication.getName());
    }
}
