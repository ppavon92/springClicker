package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
@Setter @Getter
@Data

public class Clicks {
    private int clicks;

    public Clicks() {}

    public Clicks(int clicks) {
        this.clicks = clicks;
    }
}
