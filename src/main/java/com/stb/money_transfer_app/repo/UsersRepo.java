package com.stb.money_transfer_app.repo;

import com.stb.money_transfer_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<User, Long> {
}
