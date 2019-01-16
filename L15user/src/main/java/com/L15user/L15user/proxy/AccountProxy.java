package com.L15user.L15user.proxy;

import com.L15user.L15user.bean.AccountBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "account-ms", url = "localhost:9001")
public interface AccountProxy {

	//@GetMapping(value = "/accounts_overview")
	@RequestMapping(value = "/accounts_overview", method = RequestMethod.GET)
	List<AccountBean> findAll();
	
	@RequestMapping(value = "/usr_accounts/{user}", method = RequestMethod.GET)
	List<AccountBean> findByUser(@PathVariable("user") int user);

}