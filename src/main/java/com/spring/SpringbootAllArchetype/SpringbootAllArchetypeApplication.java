package com.spring.SpringbootAllArchetype;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

@Slf4j
@SpringBootApplication
public class SpringbootAllArchetypeApplication implements ApplicationListener<ApplicationReadyEvent> {

	@Value("${custom.message}")
	String readyMessage;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAllArchetypeApplication.class, args);
	}


	/*
	* Method in Application Ready state
	*/
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		log.debug("#########################");
		log.debug(readyMessage);
		log.debug("#########################");
	}

}
