package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.controllers;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.State;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
public class StateController {
    @Autowired
    private StateRepository stateRepository;

    @PostMapping("/state")
    public ResponseEntity<?> stateAdd(@RequestParam("name") String name,
                                     @RequestParam("clicks") Long clicks) {
        State newState = new State(name, clicks);
        stateRepository.save(newState);
        return new ResponseEntity<>(newState, HttpStatus.OK);
    }

    @PostMapping("/state/add")
    public ResponseEntity<?> stateAdd(@RequestBody State newState) {
        stateRepository.save(newState);
        return new ResponseEntity<>(newState, HttpStatus.OK);
    }

    @PutMapping("/state/{id}")
    public ResponseEntity<?> stateUpdate(@PathVariable("id") Long id,
                                        @RequestBody State newState) {
        stateRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        newState.setId(id);
        stateRepository.save(newState);
        return new ResponseEntity<>(newState, HttpStatus.OK);
    }

    @DeleteMapping("/state/{id}")
    public ResponseEntity<?> stateDelete(@PathVariable("id") Long id) {
        State oldState = stateRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        stateRepository.delete(oldState);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/states")
    public ResponseEntity<Object> stateList(){
        return new ResponseEntity<>(stateRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/stateclassification")
    public ResponseEntity<Object> stateClassification() {
        return new ResponseEntity<>(stateRepository.stateClassification(),
                HttpStatus.OK);
    }
}
