package com.rmit.sept.mspersons.unit;

import com.rmit.sept.mspersons.controller.PersonController;
import com.rmit.sept.mspersons.exception.PersonIdException;
import com.rmit.sept.mspersons.model.PersonDto;
import com.rmit.sept.mspersons.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PersonControllerTest {

    @InjectMocks
    private PersonController personController;

    @Mock
    private PersonService personService;

    private HttpHeaders httpHeaders;

    @BeforeEach
    public void setUp() {
        httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "APPLICATION/JSON");
    }

    @Test
    public void postPersonRequestBody_validRequest_returnCreated() {

        PersonDto personDetails = new PersonDto();
        personDetails.setName("James");
        personDetails.setAddress("8 Speargrass St");
        personDetails.setPostcode("3233");
        personDetails.setAge("22");
        personDetails.setJob("nab of ubank");
        personDetails.setEmail("danielga22@gmail.com");
        personDetails.setPhoneNumber("0434299617");

        ResponseEntity<PersonDto> response = personController.registerNewPerson(personDetails);
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        verify(personService, times(1)).saveNewPersonDetails(personDetails);
    }

    @Test
    public void getPersonDetailsByAccNo_validRequest_returnSuccess() {

        PersonDto personDetails = new PersonDto();
        personDetails.setPersonId(11);
        personDetails.setName("James");
        personDetails.setAddress("8 Speargrass St");
        personDetails.setPostcode("3233");
        personDetails.setAge("22");
        personDetails.setJob("nab of ubank");
        personDetails.setEmail("danielga22@gmail.com");
        personDetails.setPhoneNumber("0434299617");

        when(personService.getPersonDetailsById(any())).thenReturn(Optional.of(personDetails));

        Optional<PersonDto> response = personService.getPersonDetailsById(any());
        assertThat(response.get().getName(), is("James"));
        assertThat(response.get().getPersonId(), is(11));
    }
}