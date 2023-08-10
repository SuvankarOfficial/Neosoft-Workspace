package com.contact.dto.RequestBean;

import com.contact.enums.ContactStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
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
    private ContactStatus status;

//    public Long getUserId(){
//        return contactId;
//    }

}
