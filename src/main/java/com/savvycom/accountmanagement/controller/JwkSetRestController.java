package com.savvycom.accountmanagement.controller;

import com.nimbusds.jose.jwk.JWKSet;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * endpoint cung cấp jwk cho resource server
 */
@RestController
@RequiredArgsConstructor
public class JwkSetRestController {

    private final JWKSet jwkSet;

    @GetMapping("/.well-known/jwks.json")
    public Map<String, Object> keys() {
        return this.jwkSet.toJSONObject();
    }
}
