package com.piapatza.yourticketsystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID>{
    List<UserEntity> findAll();
    Optional<UserEntity> findUserById(UUID id);
}
