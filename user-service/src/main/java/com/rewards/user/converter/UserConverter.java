package com.rewards.user.converter;

import com.rewards.user.dto.UserDTO;
import com.rewards.user.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserDTO toUserDto(User userEntity) {
        UserDTO dto = new UserDTO();
        if(null != userEntity.getManager())
            dto.setManagerId(userEntity.getManager().getId());
        dto.setManagerId(0l);
        BeanUtils.copyProperties(userEntity,dto);
        return dto;
    }
    public User toUserEntity(UserDTO userDTO, User manager) {
        User user = new User();
        user.setManager(manager);
        BeanUtils.copyProperties(userDTO,user);
        return user;
    }
}
