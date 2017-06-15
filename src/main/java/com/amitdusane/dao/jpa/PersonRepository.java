package com.amitdusane.dao.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.amitdusane.domain.Person;

public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

		
}
