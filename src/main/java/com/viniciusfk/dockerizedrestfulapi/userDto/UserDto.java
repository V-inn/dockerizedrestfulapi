package com.viniciusfk.dockerizedrestfulapi.userDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String name;
    private String password;
    private String email;

    public UserDto(String name, String password, String email){
        super();
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
