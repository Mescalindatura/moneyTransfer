package com.stb.money_transfer_app.repo;

import com.stb.money_transfer_app.model.Sender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SendersRepo extends JpaRepository<Sender, Long> {
}
