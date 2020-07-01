package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SessionRequestModel extends RequestModel{
    
    @JsonProperty(value = "session_id", required = true)
    private String SESSION_ID;

    @JsonCreator
    public SessionRequestModel(@JsonProperty(value = "email", required = true) String email,
                               @JsonProperty(value = "session_id", required = true) String session_id) {
                                   super(email);
                                   this.SESSION_ID = session_id;
    }

    @JsonProperty(value = "session_id", required = true)
    public String getSessionId() {
        return this.SESSION_ID;
    }
}