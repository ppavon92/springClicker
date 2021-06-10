package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.controllers;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import javax.persistence.EntityNotFoundException;

@RestController
public class CityController {
    @Autowired
    private CityRepository cityRepository;

    @GetMapping("/cities")
    public ResponseEntity<Object> cityList(){
        return new ResponseEntity<>(cityRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/city/{name}")
    public ResponseEntity<Object> cityDetail(@PathVariable("name") String name) {
        return new ResponseEntity<>(cityRepository.findCityByName(name),
                HttpStatus.OK);
    }

    @GetMapping(value = "/cityclassification")
    public ResponseEntity<Object> cityClassification() {
        return new ResponseEntity<>(cityRepository.cityclassification(),
                HttpStatus.OK);
    }

}
