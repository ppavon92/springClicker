package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.controllers;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Country;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
public class CountryController {
    @Autowired
    private CountryRepository countryRepository;

    @PostMapping("/country")
    public ResponseEntity<?> countryAdd(@RequestParam("name") String name,
                                     @RequestParam("clicks") Long clicks) {
        Country newCountry = new Country(name, clicks);
        countryRepository.save(newCountry);
        return new ResponseEntity<>(newCountry, HttpStatus.OK);
    }

    @PostMapping("/country/add")
    public ResponseEntity<?> countryAdd(@RequestBody Country newCountry) {
        countryRepository.save(newCountry);
        return new ResponseEntity<>(newCountry, HttpStatus.OK);
    }

    @PutMapping("/country/{id}")
    public ResponseEntity<?> countryUpdate(@PathVariable("id") Long id,
                                        @RequestBody Country newCountry) {
        countryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        newCountry.setId(id);
        countryRepository.save(newCountry);
        return new ResponseEntity<>(newCountry, HttpStatus.OK);
    }

    @DeleteMapping("/country/{id}")
    public ResponseEntity<?> countryDelete(@PathVariable("id") Long id) {
        Country oldCountry = countryRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        countryRepository.delete(oldCountry);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/countries")
    public ResponseEntity<Object> countryList(){
        return new ResponseEntity<>(countryRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/countryclassification")
    public ResponseEntity<Object> countryClassification() {
        return new ResponseEntity<>(countryRepository.countryClassification(),
                HttpStatus.OK);
    }

    @GetMapping(value = "/country/{name}")
    public ResponseEntity<Object> countryByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(countryRepository.findCountryByName(name),
                HttpStatus.OK);
    }
}
