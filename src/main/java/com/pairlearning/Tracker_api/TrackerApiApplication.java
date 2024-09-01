package com.pairlearning.Tracker_api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.pairlearning.Tracker_api.resources.AuthenticationController;

@SpringBootApplication
public class TrackerApiApplication 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(TrackerApiApplication.class, args);
		 Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	
		 logger.info("This ");
	}
}
