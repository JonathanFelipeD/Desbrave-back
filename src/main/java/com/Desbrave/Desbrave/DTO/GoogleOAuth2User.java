package com.Desbrave.Desbrave.DTO;

import java.util.Map;

public class GoogleOAuth2User {

    private String email;
    private String name;
    private String picture;

    public GoogleOAuth2User(Map<String, Object> attributes) {
        this.email = (String) attributes.get("email");
        this.name = (String) attributes.get("name");
        this.picture = (String) attributes.get("picture");
    }

    // Getters
    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getPicture() { return picture; }
}

