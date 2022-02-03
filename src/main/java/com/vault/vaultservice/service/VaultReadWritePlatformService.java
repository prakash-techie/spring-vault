package com.vault.vaultservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.vault.VaultException;
import org.springframework.vault.core.VaultKeyValueOperations;
import org.springframework.vault.core.VaultKeyValueOperationsSupport.KeyValueBackend;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.VaultResponse;

import com.vault.vaultservice.data.AdminSercret;


@Service
public class VaultReadWritePlatformService {

	private static final Logger LOG = LoggerFactory.getLogger(VaultReadWritePlatformService.class);

	
	@Autowired
	private VaultOperations operations;


	public void storeCredentials(AdminSercret secret, String path) {

		try {
			VaultKeyValueOperations keyValue = initKeyValue();
			keyValue.put(path, secret);
		} catch (VaultException e) {
			LOG.error("Exception occur during the secret store into vault : {}" + e);
		}
	}

	public String getCredentialByPath(String path) {

		String adminSercret = null;
		if (StringUtils.hasText(path)) {
			try {
				VaultKeyValueOperations keyValue = initKeyValue();
				VaultResponse response = keyValue.get(path);
				if (response != null) {
					adminSercret = response.getData().toString();
				}
			} catch (VaultException e) {
				LOG.error("Exception occur during the secret retrival from vault : {}" + e);
			}
		}
		return adminSercret;
	}

	public VaultKeyValueOperations initKeyValue() {
		return this.operations.opsForKeyValue("secret", KeyValueBackend.versioned());
	}

}
