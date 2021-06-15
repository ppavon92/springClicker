package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface CityRepository extends CrudRepository<City, Long> {
    @Query("Select c from City c where c.name = :name")
    City findCityByName(String name);

    //**JPQL doesn't admit UNION
    //Show classification with clicks sum but doesn't show players info
    @Query("Select new City(c.id, c.name, sum(p.clicks)) from City c inner join Player p on c.id = p.city.id group by c.id order by sum(p.clicks) desc")
    Collection<City> cityClassification();

    //Show classification without click sum (but calculates it) with player info
    @Query("Select c from City c inner join Player p on c.id = p.city.id group by c.id order by sum(p.clicks) desc")
    Collection<City> cityClassWPlayerInfo();
}
