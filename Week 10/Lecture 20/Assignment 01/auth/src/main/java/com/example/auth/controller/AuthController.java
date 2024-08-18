package com.example.auth.controller;

import com.example.auth.dto.ApiKeyRequest;
import com.example.auth.entity.ApiKey;
import com.example.auth.repository.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private ApiKeyRepository apiKeyRepository;

    @Autowired
    public AuthController(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @GetMapping("/validateApiKey")
    public ResponseEntity<Boolean> validateApiKey(@RequestHeader("api-key") String apiKey) {
        boolean isValid = apiKeyRepository.findByKey(apiKey).isPresent();
        return ResponseEntity.ok(isValid);
    }

    @PostMapping("/api-key")
    public ResponseEntity<ApiKey> createApiKey(@RequestBody ApiKeyRequest apiKeyRequest) {
        ApiKey newApiKey = new ApiKey();
        newApiKey.setKey(apiKeyRequest.getKey());

        ApiKey savedApiKey = apiKeyRepository.save(newApiKey);
        return ResponseEntity.ok(savedApiKey);
    }
}