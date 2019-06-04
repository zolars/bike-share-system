package database.dao.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;

import application.Main;
import database.dao.BikesDao;
import database.entity.Bikes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class BikesDaoImplTest {

    @Test
    void findAllBikes() {
        BikesDao dao = new BikesDaoImpl();
        List<Bikes> ans = new ArrayList<Bikes>();
        for (Bikes bikes : Main.bikeStationList) {
            ans.add(bikes);
        }

        try {
            assertEquals(ans.get(0).getNumber(), dao.findAllBikes().get(0).getNumber());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findBikesNumberByStation() {
        BikesDao dao = new BikesDaoImpl();
        try {
            assertEquals(5, dao.findBikesNumberByStation("A"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void changeBikesByStation() {
    }
}
