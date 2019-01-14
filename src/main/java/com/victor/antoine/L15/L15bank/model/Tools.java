package com.victor.antoine.L15.L15bank.model;

import com.victor.antoine.L15.L15bank.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Tools {

    @Autowired
    private static OperationRepository operationRepository;

    public static Double getAccountValue(String ibanSrc) {
        List<Operation> list_op = operationRepository.findByIbanSrc(ibanSrc);
        double sum = 0;
        for (Operation op : list_op) {
            sum = sum + op.getValue();
        }
        return sum;
    }

}
