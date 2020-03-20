package com.checkout.management.apputil;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.checkout.management.ivalidator.IDataRequestValidator;
import com.checkout.management.validatorimpl.DataRequestValidatorImp;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

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
	

	/** 
	 * Creating Docket bean
	 *@return Docket
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(AppConstant.DEFAULT_API_INFO)
				.produces(AppConstant.DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(AppConstant.DEFAULT_PRODUCES_AND_CONSUMES);
	}
	
}
