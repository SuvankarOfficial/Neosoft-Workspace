package com.monolith.beans.RequestBean;

import com.monolith.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequestBean {

    @JsonProperty(value = "contact_id")
    private Long contactId;

    @JsonProperty(value = "contact_name")
    private String contactName;

    @JsonProperty(value = "contact_number")
    private String number;

    @JsonProperty(value = "user_id")
    private Long userId;

    @JsonIgnore
    private Status status;

//    public Long getUserId(){
//        return contactId;
//    }

}
