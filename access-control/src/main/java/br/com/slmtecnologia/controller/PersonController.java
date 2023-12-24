package br.com.slmtecnologia.controller;

import br.com.slmtecnologia.controller.dto.PersonDto;
import br.com.slmtecnologia.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    IPersonService personService;

    @GetMapping
    public List<PersonDto> findAllPersons(){

        return personService.getPersons();
    }


}
