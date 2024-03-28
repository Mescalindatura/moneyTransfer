package com.stb.money_transfer_app.repo;

import com.stb.money_transfer_app.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationsRepo extends JpaRepository<Operation, Long> {
}
