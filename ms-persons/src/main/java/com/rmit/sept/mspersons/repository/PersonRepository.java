package com.rmit.sept.mspersons.repository;

import com.rmit.sept.mspersons.model.PersonDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<PersonDto, Integer> {
}
