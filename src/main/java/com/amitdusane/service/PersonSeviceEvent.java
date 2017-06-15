package com.amitdusane.service;

import org.springframework.context.ApplicationEvent;

import com.amitdusane.domain.Person;

public class PersonSeviceEvent extends ApplicationEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Person eventPerson;
	String eventType;
	
	
	public PersonSeviceEvent(Object source, Person eventPerson, String eventType) {
		super(source);
		this.eventPerson = eventPerson;
		this.eventType = eventType;
	}


	public Person getEventPerson() {
		return eventPerson;
	}


	public void setEventPerson(Person eventPerson) {
		this.eventPerson = eventPerson;
	}


	public String getEventType() {
		return eventType;
	}


	public void setEventType(String eventType) {
		this.eventType = eventType;
	}


	@Override
	public String toString() {
		return "PersonSeviceEvent [eventPerson=" + eventPerson + ", eventType=" + eventType + "]";
	}

}
