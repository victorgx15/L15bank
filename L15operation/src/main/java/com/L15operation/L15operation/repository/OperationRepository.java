package com.L15operation.L15operation.repository;

import com.L15operation.L15operation.model.Operation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends CrudRepository<Operation, Integer> {
    List<Operation> findByIbanSrcOrIbanDest(String ibanSrc, String ibanDest);
    List<Operation> findByDate(String date);
    List<Operation> findByType(String type);
    List<Operation> findAll();
}
