package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel {

    @JsonProperty(value = "resultCode", required = true)
    private Integer RESULTCODE;
    @JsonProperty(value = "message", required = true)
    private String MESSAGE;
    @JsonProperty(value = "sessionId")
    private String SESSIONID;

    @JsonCreator
    public ResponseModel(@JsonProperty(value = "resultCode", required = true) Integer resultCode, 
                         @JsonProperty(value = "message", required = true) String message,
                         @JsonProperty(value = "sessionId") String sessionId) {
        this.RESULTCODE = resultCode;
        this.MESSAGE = message;
        this.SESSIONID = sessionId;
    }

    @JsonProperty(value = "resultCode")
    public Integer getResultCode() {
        return this.RESULTCODE;
    }

    @JsonProperty(value = "message")
    public String getMessage() {
        return this.MESSAGE;
    }

    @JsonProperty(value = "sessionId")
    public String getSessionId() {
        return this.SESSIONID;
    }
}