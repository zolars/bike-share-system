package database;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class BaseDaoTest {

    @Test
    void search() {
    }

    @Test
    void addLine() {
        try {
            BaseDao.replace("msg.txt", "3", 0, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void replace() {
    }

    @Test
    void dataAmount() {
    }

    @Test
    void main() {
    }
}