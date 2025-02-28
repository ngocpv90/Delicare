package com.app.delicare.mappers.base;
import com.app.delicare.entitys.users.User;
import com.app.delicare.responses.base.CreateByUserResponse;
import com.app.delicare.responses.base.ModifiedByUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BaseMapper {
    public CreateByUserResponse mapCreateByUserResponseToEntity(User user){
        return CreateByUserResponse.builder()
                .id(user.getId())
                .userName(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .build();
    }
    public ModifiedByUserResponse mapModifiedByUserResponseToEntity(User user) {
        return ModifiedByUserResponse.builder()
                .id(user.getId())
                .userName(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .build();
    }
}
