package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.State;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface StateRepository extends CrudRepository<State, Long> {
    @Query("Select s from State s where s.name = :name")
    State findStateByName(String name);

    @Query("Select new State(s.id, s.name, sum(p.clicks)) from State s inner join City c on s.id=c.state.id inner join Player p on c.id = p.city.id group by s.id order by sum(p.clicks) desc")
    Collection<State> stateClassification();

//    @Query("Select new State(s.cities) from State s inner join City c on s.id=c.state.id inner join Player p on c.id = p.city.id group by s.id order by sum(p.clicks) desc")
//    Collection<State> stateClassification();

//    @Query("Select new es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.State(s.id, s.name, sum(p.clicks), s.cities) from State s inner join City c on s.id=c.state.id inner join Player p on c.id = p.city.id group by s.id order by sum(p.clicks) desc")
//    Collection<State> stateClassification();

}
