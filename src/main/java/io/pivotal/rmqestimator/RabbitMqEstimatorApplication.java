package io.pivotal.rmqestimator;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@Configuration
@EnableJpaRepositories
@Import(RepositoryRestMvcConfiguration.class)
@ComponentScan
@EnableAutoConfiguration
public class RabbitMqEstimatorApplication {

    public static void main(String[] args) {
    	SpringApplication app=new SpringApplication(RabbitMqEstimatorApplication.class);
    	app.setShowBanner(false);
    	app.run(args);
    }
}
