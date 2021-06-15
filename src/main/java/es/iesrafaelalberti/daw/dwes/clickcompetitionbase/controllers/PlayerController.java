package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.controllers;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Player;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    @PostMapping("/player")
    public ResponseEntity<?> playerAdd(@RequestParam("name") String name,
                                       @RequestParam("clicks") int clicks) {
        Player newPlayer = new Player(name, clicks, null);
        playerRepository.save(newPlayer);
        return new ResponseEntity<>(newPlayer, HttpStatus.OK);
    }

    @PostMapping("/player/add")
    public ResponseEntity<?> playerAdd(@RequestBody Player newPlayer) {
        playerRepository.save(newPlayer);
        return new ResponseEntity<>(newPlayer, HttpStatus.OK);
    }

    @PutMapping("/player/{id}")
    public ResponseEntity<?> playerUpdate(@PathVariable("id") Long id,
                                          @RequestBody Player newPlayer) {
        playerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        newPlayer.setId(id);
        playerRepository.save(newPlayer);
        return new ResponseEntity<>(newPlayer, HttpStatus.OK);
    }

    @DeleteMapping("/player/{id}")
    public ResponseEntity<?> playerDelete(@PathVariable("id") Long id) {
        Player oldPlayer = playerRepository.findById(id)
                                           .orElseThrow(() -> new EntityNotFoundException());
        playerRepository.delete(oldPlayer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping("/players")
    public ResponseEntity<Object> playerList() {
        return new ResponseEntity<>(playerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/player/{id}")
    public ResponseEntity<Object> playerDetail(@PathVariable("id") Long id) {
        return new ResponseEntity<>(playerRepository.findById(id).orElseThrow(EntityNotFoundException::new),
                                    HttpStatus.OK);
    }

    @GetMapping(value = "/player/morethan/{clicks}")
    public ResponseEntity<Object> bestPlayers(@PathVariable("clicks") Integer clicks) {
        return new ResponseEntity<>(playerRepository.findPlayerByClicks(clicks), HttpStatus.OK);
    }

    @GetMapping(value = "/player/from/{name}")
    public ResponseEntity<Object> playerByCity(@PathVariable("name") String name) {
        return new ResponseEntity<>(playerRepository.findPlayerByCity(name), HttpStatus.OK);
    }

    @GetMapping(value = "/playerbycity")
    public ResponseEntity<Object> groupPlayerByCity() {
        return new ResponseEntity<>(playerRepository.groupPlayerByCity(), HttpStatus.OK);
    }

}
