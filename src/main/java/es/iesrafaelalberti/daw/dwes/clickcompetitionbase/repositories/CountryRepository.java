package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface CountryRepository extends CrudRepository<Country, Long> {
    @Query("Select c from Country c where c.name = :name")
    Country findCountryByName(String name);

    @Query("Select new Country(c.id, c.name, sum(p.clicks)) from Country c inner join State s on c.id=s.country.id inner join City ci on s.id=ci.state.id inner join Player p on ci.id = p.city.id group by c.id order by sum(p.clicks) desc")
    Collection<Country> countryClassification();
}
