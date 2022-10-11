package com.rmit.sept.mspersons.repository;

import com.rmit.sept.mspersons.model.PersonDto;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<PersonDto, Integer> {
}
