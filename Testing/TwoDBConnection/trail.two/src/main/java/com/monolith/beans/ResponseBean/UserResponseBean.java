package com.user.beans.ResponseBean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseBean {

    @JsonProperty(value = "user_name")
    private String userName;

    @JsonProperty(value = "user_number")
    private String number;

}
