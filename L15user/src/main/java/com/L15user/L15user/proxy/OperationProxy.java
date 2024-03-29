package com.L15user.L15user.proxy;

import com.L15user.L15user.bean.OperationBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "operation-ms", url = "localhost:9020")
public interface OperationProxy {
	
	@GetMapping(value = "/operations")
	List<OperationBean> showOps(@RequestParam String iban, @RequestParam String date, @RequestParam String type);
	
	@PostMapping(value = "/createTransfer")
	OperationBean makeTransfer(@RequestBody OperationBean op);

}