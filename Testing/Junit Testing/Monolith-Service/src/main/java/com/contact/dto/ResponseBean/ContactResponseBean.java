package com.contact.dto.ResponseBean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactResponseBean {

    @JsonProperty(value = "contact_name")
    private String contactName;

    @JsonProperty(value = "contact_number")
    private String number;

    @JsonProperty(value = "user_id")
    private Long userId;

}
