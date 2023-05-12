package org.dragon.yunpeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = { "org.dragon.yunpeng" })
@EnableJpaRepositories(basePackages = "org.dragon.yunpeng.repositories")
@EnableTransactionManagement
@EntityScan(basePackages = "org.dragon.yunpeng.entities")
public class SpringBootSandBoxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSandBoxApplication.class, args);
	}

	// Register HttpSessionEventPublisher; otherwise, session events are not
	// captured by listeners.
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

}
