package com.amitdusane.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/*
 * This class can be used for Audit Trail/Sending email functionality. It will save audit data asynchronously.
 * (It works in a separate thread)
 */
@Component
public class PersonServiceEventListener implements ApplicationListener<PersonSeviceEvent> {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void onApplicationEvent(PersonSeviceEvent pse) {
		
		log.info("Person + " + pse.getEventType() + " with details: " + pse.getEventPerson());	
		
	}

}
