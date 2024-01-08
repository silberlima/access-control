package com.slmtecnologia.unitTests.mockito.service;

import com.slmtecnologia.model.entity.Person;
import com.slmtecnologia.repository.PersonRepository;
import com.slmtecnologia.service.core.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonServiceTest {

    @Mock
    PersonRepository repository;

    @InjectMocks
    PersonService service;

    Person personEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        createNewPerson();
    }

    private void createNewPerson(){
        personEntity =  new Person("João", "00011122233", "joao@mail.com");
    }

    @Test
    void getPersonById() {
        Long id = 1L;
        personEntity.setId(id);
        when(repository.findById(id)).thenReturn(Optional.ofNullable(personEntity));

        var result =  service.getPersonById(id);

        assertNotNull(result);
        assertEquals(result, personEntity);
    }


}