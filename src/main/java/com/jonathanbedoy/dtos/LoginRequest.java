package com.jonathanbedoy.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
//@NoArgsConstructor
public class LoginRequest {
    private String email;
    private String password;
}
