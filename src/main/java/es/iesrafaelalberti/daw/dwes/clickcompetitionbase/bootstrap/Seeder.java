package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.bootstrap;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.City;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Player;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.CityRepository;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements CommandLineRunner {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private CityRepository cityRepository;


    public void run(String... args) throws Exception {
        // create a new city
        City city1 = cityRepository.save(new City("Madrid"));
        City city2 = cityRepository.save(new City("Sevilla"));
        City city3 = cityRepository.save(new City("Jerez"));

        playerRepository.save(new Player("Juan", 3, city1));
        playerRepository.save(new Player("María", 10, city1));
        playerRepository.save(new Player("Blanca", 22, city3));
        playerRepository.save(new Player("Carlos", 7, city2));
        playerRepository.save(new Player("Sandra", 13, city2));
        playerRepository.save(new Player("Félix", 5, city2));
    }



}
