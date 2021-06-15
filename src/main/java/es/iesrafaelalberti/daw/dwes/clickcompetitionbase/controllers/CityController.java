package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.controllers;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.City;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.persistence.EntityNotFoundException;

@RestController
public class CityController {
    @Autowired
    private CityRepository cityRepository;

    @PostMapping("/city")
    public ResponseEntity<?> cityAdd(@RequestParam("name") String name,
                                       @RequestParam("clicks") Long clicks) {
        City newCity = new City(name, clicks);
        cityRepository.save(newCity);
        return new ResponseEntity<>(newCity, HttpStatus.OK);
    }

    @PostMapping("/city/add")
    public ResponseEntity<?> cityAdd(@RequestBody City newCity) {
        cityRepository.save(newCity);
        return new ResponseEntity<>(newCity, HttpStatus.OK);
    }

    @PutMapping("/city/{id}")
    public ResponseEntity<?> cityUpdate(@PathVariable("id") Long id,
                                          @RequestBody City newCity) {
        cityRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        newCity.setId(id);
        cityRepository.save(newCity);
        return new ResponseEntity<>(newCity, HttpStatus.OK);
    }

    @DeleteMapping("/city/{id}")
    public ResponseEntity<?> cityDelete(@PathVariable("id") Long id) {
        City oldCity = cityRepository.findById(id)
                                           .orElseThrow(EntityNotFoundException::new);
        cityRepository.delete(oldCity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
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
        return new ResponseEntity<>(cityRepository.cityClassification(),
                HttpStatus.OK);
    }

    @GetMapping(value = "/cityclasswcityinfo")
    public ResponseEntity<Object> cityclasswCityInfo() {
        return new ResponseEntity<>(cityRepository.cityClassWPlayerInfo(),
                HttpStatus.OK);
    }

}
