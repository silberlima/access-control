package br.com.slmtecnologia.service;

import br.com.slmtecnologia.controller.dto.PersonDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPersonService {
    List<PersonDto> getPersons();
}
