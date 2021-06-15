package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.bootstrap;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.*;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class Seeder implements CommandLineRunner {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerTeamRepository playerTeamRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CountryRepository countryRepository;

    public void run(String... args) throws Exception {

        // create a new team
        Team team1 = teamRepository.save(new Team("Frankfurt"));
        Team team2 = teamRepository.save(new Team("Jumbo"));
        Team team3 = teamRepository.save(new Team("Viena"));

        // create a new country
        Country country1 = countryRepository.save(new Country("España"));
        Country country2 = countryRepository.save(new Country("Francia"));

        // create a new state
        State state1 = stateRepository.save(new State("Andalucía", country1));
        State state2 = stateRepository.save(new State("Cataluña", country1));

        // create a new city
        City city1 = cityRepository.save(new City("Barcelona", state2));
        City city2 = cityRepository.save(new City("Sevilla", state1));
        City city3 = cityRepository.save(new City("Jerez", state1));

        playerRepository.save(new Player("Juan", 3, city1, Set.of(team1,team2)));
        playerRepository.save(new Player("María", 10, city1, Set.of(team1,team3)));
        playerRepository.save(new Player("Blanca", 22, city3, Set.of(team3,team2)));
        playerRepository.save(new Player("Carlos", 7, city2, Set.of(team1)));
        playerRepository.save(new Player("Sandra", 13, city2, Set.of(team2)));
        playerRepository.save(new Player("Félix", 5, city2, Set.of(team3)));

    }
}
