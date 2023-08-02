package com.monolith.mapper;

import com.monolith.beans.RequestBean.UserRequestBean;
import com.monolith.entity.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User requestEntityMapperUpdate(UserRequestBean userRequestBean, User user){
        return user.builder()
                .userId(userRequestBean.getUserId() != null ? userRequestBean.getUserId() : user.getUserId())
                .userName(userRequestBean.getUserName() != null ? userRequestBean.getUserName(): user.getUserName())
                .number(userRequestBean.getNumber() != null ? userRequestBean.getNumber() : user.getNumber())
                .build();
    }

    public com.user.beans.ResponseBean.UserResponseBean entityResponseMapper(User user){
        com.user.beans.ResponseBean.UserResponseBean userResponseBean = null;
        return userResponseBean.builder()
                .userName(user.getUserName())
                .number(user.getNumber())
                .build();
    }

    public User requestEntityMapperCreate(UserRequestBean userRequestBean) {
        User user = null;
        return user.builder()
                .userName(userRequestBean.getUserName())
                .number(userRequestBean.getNumber())
                .build();
    }
}
