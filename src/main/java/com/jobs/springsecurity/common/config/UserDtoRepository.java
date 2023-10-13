package com.jobs.springsecurity.common.config;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDtoRepository extends JpaRepository<UserDto, String> {
}
