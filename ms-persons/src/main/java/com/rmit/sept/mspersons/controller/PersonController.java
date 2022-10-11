package com.rmit.sept.mspersons.controller;

import com.rmit.sept.mspersons.model.PersonDto;
import com.rmit.sept.mspersons.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping(path="/person/register")
    public ResponseEntity<PersonDto> registerNewPerson(@RequestBody PersonDto personDetails) {
        return new ResponseEntity<>(personService.saveNewPersonDetails(personDetails), HttpStatus.CREATED);
    }

    @GetMapping(path="/persons/person")
    public List<PersonDto> getAllPersonDetails() {
        return personService.getAllPeopleDetails();
    }

    @GetMapping(path="/person/{personId}")
    public Optional<PersonDto> getAccountDetailsById(@PathVariable Integer personId) {
        return personService.getPersonDetailsById(personId);
    }

    @PutMapping(path="/person/{personId}")
    public ResponseEntity<PersonDto> updateExistingPersonDetails(@PathVariable Integer personId, @RequestBody PersonDto newPersonDetails) throws Exception {
        return new ResponseEntity<>(personService.updateExistingPersonDetails(personId, newPersonDetails), HttpStatus.OK);
    }

    @DeleteMapping(path="/person/{personId}")
    public void deleteExistingPersonDetails(@PathVariable Integer personId) {
        personService.deleteExistingPersonDetails(personId);
    }
}
