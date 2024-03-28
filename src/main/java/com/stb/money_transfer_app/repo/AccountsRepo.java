package com.stb.money_transfer_app.repo;

import com.stb.money_transfer_app.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepo extends JpaRepository<Account, Long> {
}
