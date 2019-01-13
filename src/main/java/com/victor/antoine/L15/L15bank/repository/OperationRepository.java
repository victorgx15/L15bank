package com.victor.antoine.L15.L15bank.repository;

import com.victor.antoine.L15.L15bank.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Integer> {
    List<Operation> findByIban_source(String iban_source);
}
