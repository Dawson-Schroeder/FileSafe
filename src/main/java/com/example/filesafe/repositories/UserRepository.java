package com.example.filesafe.repositories;

import com.example.filesafe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
