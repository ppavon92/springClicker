package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.controllers;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Player;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

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
