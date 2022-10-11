package com.rmit.sept.mspersons.service;

import com.rmit.sept.mspersons.exception.PersonIdException;
import com.rmit.sept.mspersons.model.PersonDto;
import com.rmit.sept.mspersons.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonDto saveNewPersonDetails(PersonDto personDetails) {
        return personRepository.save(personDetails);
    }

    public List<PersonDto> getAllPeopleDetails() {
        return (List<PersonDto>) personRepository.findAll();
    }

    public Optional<PersonDto> getPersonDetailsById(Integer personId) {

        Optional<PersonDto> personDetails = personRepository.findById(personId);

        if (!personDetails.isPresent()) {

            throw new PersonIdException("Person Id is not found in the database", personId);
        }
        return personDetails;
    }

    public PersonDto updateExistingPersonDetails(Integer personId, PersonDto newPersonDetails) throws Exception {

        Optional<PersonDto> personDetails = personRepository.findById(personId);

        if (personDetails.isPresent()) {
            personDetails.get().setName(newPersonDetails.getName());
            personDetails.get().setAddress(newPersonDetails.getAddress());
            personDetails.get().setPostcode(newPersonDetails.getPostcode());
            personDetails.get().setAge(newPersonDetails.getAge());
            personDetails.get().setJob(newPersonDetails.getJob());
            personDetails.get().setEmail(newPersonDetails.getEmail());
            personDetails.get().setPhoneNumber(newPersonDetails.getPhoneNumber());

            log.info("Fetching old person details={} from database", personDetails);
            log.info("Updating new person details={}", newPersonDetails);

            return personRepository.save(personDetails.get());
        }
        throw new Exception("Invalid request, please check your person Id");
    }

    public void deleteExistingPersonDetails(Integer personId) {
        personRepository.deleteById(personId);
    }

}
