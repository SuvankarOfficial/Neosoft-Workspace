package com.travel.experience.listing.bean.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceResponseBean {

    private Boolean status;

    private String message;

    private String error;

    private Object data;

}
