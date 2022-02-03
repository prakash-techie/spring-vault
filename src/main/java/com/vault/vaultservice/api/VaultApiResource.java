package com.vault.vaultservice.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vault.vaultservice.data.AdminSercret;
import com.vault.vaultservice.service.VaultReadWritePlatformService;


@RestController
@RequestMapping("/vault")
public class VaultApiResource {
	
	@Autowired
	private VaultReadWritePlatformService vaultReadWritePlatformService;

	@RequestMapping(method = RequestMethod.POST)
	public void StoreSecret(@RequestParam final String path,@RequestBody final AdminSercret data) {
		this.vaultReadWritePlatformService.storeCredentials(data,path); 
	}

	@RequestMapping(method = RequestMethod.GET, path ="/get-secret")
	public String getSecretByPath(@RequestParam final String path) {
		return this.vaultReadWritePlatformService.getCredentialByPath(path);
	}
	

}
