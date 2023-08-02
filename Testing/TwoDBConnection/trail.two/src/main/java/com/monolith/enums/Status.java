package com.monolith.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public enum Status {

    Active("Active"),

    Inactive("Inactive"),

    Deleted("Deleted");

    @Getter
    private String value;

    public static List<String> getValue(){
        return List.of(Status.values()).stream().map(data-> data.value).collect(Collectors.toList());
    }

}
