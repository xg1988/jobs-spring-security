package com.jobs.springsecurity.common.config;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
@Entity
public class UserDto {

    @Id
    String userId;
    String email;
    String password;
    String name;
    String role;
}
