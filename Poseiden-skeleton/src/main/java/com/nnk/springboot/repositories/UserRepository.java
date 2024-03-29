package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserApp, Integer>, JpaSpecificationExecutor<UserApp> {
    Optional<UserApp> findByUserName(String userName);
}
