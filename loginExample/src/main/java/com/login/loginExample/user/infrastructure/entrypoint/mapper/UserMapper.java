package com.login.loginExample.user.infrastructure.entrypoint.mapper;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.login.loginExample.user.domain.entity.User;
import com.login.loginExample.user.infrastructure.entrypoint.dto.request.UserRequestDto;
import com.login.loginExample.user.infrastructure.entrypoint.dto.response.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private static ObjectMapper objectMapper = new ObjectMapper();
    public static UserResponseDto convertUserToResponseDTO (User user){
        return objectMapper.convertValue(user, UserResponseDto.class);
    }

    public static User convertRequestToUser(UserRequestDto userRequestDto) {
        return objectMapper.convertValue(userRequestDto, User.class);
    }
}
