package com.L15user.L15user.proxy;

import com.L15user.L15user.bean.OperationBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "operation-ms", url = "localhost:9020")
public interface OperationProxy {
	
	@GetMapping(value = "/operations/{iban}")
	List<OperationBean> operationsList(@PathVariable("iban") String iban);

}