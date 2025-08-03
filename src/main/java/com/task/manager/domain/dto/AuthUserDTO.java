package com.task.manager.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthUserDTO {
        

    private String passwordHash;   // Base64(PBKDF2 hash)


    private String salt;       
}
