package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.controllers;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Player;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Team;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.PlayerRepository;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
public class TeamController {
    @Autowired
    private TeamRepository teamRepository;

    @PostMapping("/team")
    public ResponseEntity<?> teamAdd(@RequestParam("name") String name) {
        Team newTeam = new Team(name);
        teamRepository.save(newTeam);
        return new ResponseEntity<>(newTeam, HttpStatus.OK);
    }

    @PostMapping("/team/add")
    public ResponseEntity<?> teamAdd(@RequestBody Team newTeam) {
        teamRepository.save(newTeam);
        return new ResponseEntity<>(newTeam, HttpStatus.OK);
    }

    @PutMapping("/team/{id}")
    public ResponseEntity<?> teamUpdate(@PathVariable("id") Long id,
                                          @RequestBody Team newTeam) {
        teamRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        newTeam.setId(id);
        teamRepository.save(newTeam);
        return new ResponseEntity<>(newTeam, HttpStatus.OK);
    }

    @DeleteMapping("/team/{id}")
    public ResponseEntity<?> teamDelete(@PathVariable("id") Long id) {
        Team oldTeam = teamRepository.findById(id)
                                           .orElseThrow(() -> new EntityNotFoundException());
        teamRepository.delete(oldTeam);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping("/teams")
    public ResponseEntity<Object> teamList() {
        return new ResponseEntity<>(teamRepository.findAll(), HttpStatus.OK);
    }

//    @GetMapping(value = "/team/{id}")
//    public ResponseEntity<Object> teamDetail(@PathVariable("id") Long id) {
//        return new ResponseEntity<>(teamRepository.findById(id).orElseThrow(EntityNotFoundException::new),
//                                    HttpStatus.OK);
//    }

    @GetMapping(value = "/team/{name}")
    public ResponseEntity<Object> teamByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(teamRepository.findTeamByName(name),
                HttpStatus.OK);
    }

    @GetMapping(value = "/team/{name}/players")
    public ResponseEntity<Object> playerByTeamByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(teamRepository.findTeamByName(name).getPlayers(),
                HttpStatus.OK);
    }
}
