package com.example.demo.controller;

import com.example.demo.entity.UserInfo;
import com.example.demo.model.ProfileModel;
import com.example.demo.service.interfaces.ProfileService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/identity")
@CrossOrigin
public class IdentityController {
    @Autowired
    ProfileService profileService;

    @GetMapping()
    public ProfileModel getUserInfo() {
        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext()
                .getAuthentication();

        final Principal principal = (Principal) authentication.getPrincipal();

        String dob = "";
        String userIdByToken = "";
        String userIdByMapper = "";

        if (principal instanceof KeycloakPrincipal) {
            KeycloakPrincipal<KeycloakSecurityContext> kPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
            var token = kPrincipal.getKeycloakSecurityContext().getToken();

            userIdByToken = token.getSubject();
            userIdByMapper = token.getOtherClaims().get("sid").toString();

            Map<String, Object> customClaims = token.getOtherClaims();

            if (customClaims.containsKey("DOB")) {
                dob = String.valueOf(customClaims.get("DOB"));
            }

            var profile = profileService.findByKeycloakId(userIdByToken);
            return profile;
        }
        return null;
    }
}
