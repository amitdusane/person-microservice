package com.amitdusane.api.rest;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.amitdusane.domain.Person;
import com.amitdusane.exception.Http404Exception;
import com.amitdusane.service.PersonService;
import com.amitdusane.service.PersonSeviceEvent;

@RestController
@RequestMapping(value="/api/rest/v1/person")
public class PersonController extends AbstractRestController {
	
	@Autowired
	private PersonService service;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Page<Person> getAllPerson(Pageable pageable){
		
		return service.getAllPerson(pageable);		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Person getPerson(@PathVariable("id") Long id){
		
		Person person = service.getPerson(id);
		checkResourceFound(person);
		return person;		
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void createPerson(@RequestBody Person person, HttpServletRequest request, HttpServletResponse response){
		
		Person createdPerson = service.createPerson(person);
		applicationEventPublisher.publishEvent(new PersonSeviceEvent(this, createdPerson, "PersonCreated"));
		response.setHeader("Location", request.getRequestURL().append("/").append(createdPerson.getId()).toString());
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePerson(@PathVariable("id") Long id,  @RequestBody Person person){
		
		checkResourceFound(service.getPerson(id));
		
		if(id != person.getId()){
			throw new Http404Exception("Resource not found!");
		}
		service.updatePerson(person);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePerson(@PathVariable("id") Long id){
		
		checkResourceFound(service.getPerson(id));				
		service.deletePerson(id);
	}
	

}
