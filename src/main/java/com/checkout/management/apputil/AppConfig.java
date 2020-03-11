package com.checkout.management.apputil;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.checkout.management.ivalidator.IDataRequestValidator;
import com.checkout.management.validatorimpl.DataRequestValidatorImp;

/**
 * Create AppConfig class Creating other Bean
 * 
 * @author RanjeetSi
 */
@Configuration
public class AppConfig {

	/**
	 * Creating bean for data request validator
	 * 
	 * @return DataRequestValidatorImp
	 *
	 */
	@Bean
	IDataRequestValidator findIValidator() {
		return new DataRequestValidatorImp();
	}

	/**
	 * Create Bean of rest template
	 * @return
	 */
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
