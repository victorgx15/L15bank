package com.victor.antoine.L15.L15bank.repository;

import com.victor.antoine.L15.L15bank.model.Operation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OperationRepository extends CrudRepository<Operation, Integer> {
    List<Operation> findByIbanSrc(String ibanSrc);

    List<Operation> findAll();
}
