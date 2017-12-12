package com.amitdusane.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amitdusane.dao.jpa.PersonRepository;
import com.amitdusane.domain.Person;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;
	
	@Autowired
	private CounterService cntService;	
	
	public PersonService() {
		
	}
	
	public Person getPerson(Long id){
		return repository.findOne(id);
	}
	
	public Person createPerson(Person person){
		return repository.save(person);
	}
	
	public void updatePerson(Person person){
		repository.save(person);
	}
	
	public void deletePerson(Long id){
		repository.delete(id);
	}
	
	public Page<Person> getAllPerson(Pageable pageable){
		//Page<Person> personPage = repository.findAll(new PageRequest(page, size));
		Page<Person> personPage = repository.findAll(pageable);
		
		if(personPage != null && personPage.getSize() > 50){
			cntService.increment("com.amitdusane.service.PersonService.allPerson.largePayload");
		}
		return personPage;
	}
	
}
