package io.spring.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.FunctionScan;
import org.springframework.cloud.function.registry.FunctionCatalog;
import org.springframework.context.ConfigurableApplicationContext;

@FunctionScan
@SpringBootApplication
public class FunctionRunner {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(FunctionRunner.class, args);
		FunctionCatalog catalog = context.getBean(FunctionCatalog.class);
		System.out.println("greeter:   " + catalog.lookupFunction("greeter"));
		System.out.println("uppercase: " + catalog.lookupFunction("uppercase"));
		System.out.println("lowercase: " + catalog.lookupFunction("lowercase"));
	}
}
