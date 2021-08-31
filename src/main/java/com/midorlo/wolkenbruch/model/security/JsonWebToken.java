package com.midorlo.wolkenbruch.model.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.midorlo.wolkenbruch.rest.auth.AuthResource;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a JSON Web Token.
 * Used as a header field after successfully authenticating
 * via {@link AuthResource}
 */
@Data
@AllArgsConstructor
public class JsonWebToken {

    @JsonProperty("id_token")
    private String idToken;
}
