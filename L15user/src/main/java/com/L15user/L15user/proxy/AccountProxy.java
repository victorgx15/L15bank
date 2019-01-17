package com.L15user.L15user.proxy;

import com.L15user.L15user.bean.AccountBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "account-ms", url = "localhost:9001")
public interface AccountProxy {
	
	@GetMapping(value = "/usr_accounts/{user}")
	List<AccountBean> findByUser(@PathVariable("user") int user);
	
	@PostMapping(value = "/addAccount")
	AccountBean addAccountToNewUser(@RequestBody AccountBean acc);
	
	@PutMapping(value = "/editAccount")
	AccountBean editAccount(@RequestBody AccountBean acc);
	
	@DeleteMapping(value = "/delAccount")
	void deleAccount(@RequestParam int idd);

}