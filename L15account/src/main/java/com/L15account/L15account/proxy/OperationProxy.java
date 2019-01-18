package com.L15account.L15account.proxy;

import com.L15account.L15account.bean.OperationBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "operation-ms", url = "localhost:9020")
public interface OperationProxy {
	
	@PostMapping(value = "/createTransfer")
	OperationBean makeTransfer(@RequestBody OperationBean op);

}