package com.stb.money_transfer_app.repo;

import com.stb.money_transfer_app.model.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipientsRepo extends JpaRepository<Recipient, Long> {
}
