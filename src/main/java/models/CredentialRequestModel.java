package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CredentialRequestModel extends RequestModel{

    @JsonProperty(value = "password", required = true)
    private String PASSWORD;

    @JsonCreator
    public CredentialRequestModel(@JsonProperty(value = "email", required = true) String email,
                                  @JsonProperty(value = "password", required = true) String password) {
        super(email);
        this.PASSWORD = password;
    }

    @JsonProperty(value = "password")
    public String getPassword() {
        return this.PASSWORD;
    }
}