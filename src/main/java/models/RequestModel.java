package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestModel {

    @JsonProperty(value = "email", required = true)
    private String EMAIL;
    
    @JsonCreator
    public RequestModel(@JsonProperty(value = "email", required = true) String email) {
        this.EMAIL = email;
    }

    @JsonProperty(value = "email")
    public String getEmail() {
        return this.EMAIL;
    }
}