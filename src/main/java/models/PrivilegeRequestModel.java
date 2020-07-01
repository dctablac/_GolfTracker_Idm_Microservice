package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PrivilegeRequestModel extends RequestModel {
    
    @JsonProperty(value = "plevel", required = true)
    private Integer PLEVEL;

    @JsonCreator
    public PrivilegeRequestModel(@JsonProperty(value = "email", required = true) String email,
                                 @JsonProperty(value = "plevel", required = true) Integer plevel) {
        super(email);
        this.PLEVEL = plevel;
    }

    @JsonProperty(value = "plevel", required = true)
    public Integer getPlevel() {
        return this.PLEVEL;
    }
}