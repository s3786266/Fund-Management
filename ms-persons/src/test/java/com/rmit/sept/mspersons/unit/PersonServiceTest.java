package com.rmit.sept.mspersons.unit;

import com.rmit.sept.mspersons.exception.PersonIdException;
import com.rmit.sept.mspersons.model.PersonDto;
import com.rmit.sept.mspersons.repository.PersonRepository;
import com.rmit.sept.mspersons.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Test
    public void getPersonId_validRequest_returnSuccess() {

        PersonDto personDetails = new PersonDto();
        personDetails.setPersonId(11);
        personDetails.setName("James");
        personDetails.setAddress("8 Speargreass St");
        personDetails.setPostcode("3233");
        personDetails.setAge("22");
        personDetails.setJob("nab of ubank");
        personDetails.setEmail("danielga22@gmail.com");
        personDetails.setPhoneNumber("0434299617");

        when(personRepository.findById(11)).thenReturn(Optional.of(personDetails));

        Optional<PersonDto> result = personService.getPersonDetailsById(11);

        assertThat(result.get().getName(), is("James"));
        assertThat(result.get().getPostcode(), is("3233"));
        assertThat(result.get().getPersonId(), is(11));
        verify(personRepository).findById(11);
    }

    @Test
    public void getPersonDetailsById_invalidRequest_returnError() throws Exception {

        Integer invalidPersonId = 11;

        PersonDto personDetails = new PersonDto();
        personDetails.setPersonId(12);
        personDetails.setName("James");
        personDetails.setAddress("8 Speargreass St");
        personDetails.setPostcode("3233");
        personDetails.setAge("22");
        personDetails.setJob("nab of ubank");
        personDetails.setEmail("danielga22@gmail.com");
        personDetails.setPhoneNumber("0434299617");

        when(personRepository.findById(12)).thenReturn(Optional.of(personDetails));

        assertThatThrownBy(() -> personService.getPersonDetailsById(invalidPersonId))
                .isInstanceOf(PersonIdException.class)
                .hasMessageContaining("Person Id is not found in the database");
    }
}
