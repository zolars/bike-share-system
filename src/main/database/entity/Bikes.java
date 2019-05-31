package database.entity;

/**
 * Bikes
 * 
 * @author Xin Yifei
 * @version 1.0
 */
public class Bikes {
    private String station = new String();
    private int number;

    public Bikes() {
    }

    public Bikes(String station, int number) {
        this.station = station;
        this.number = number;
    }

    /**
     * @param station the station to set
     */
    public void setStation(String station) {
        this.station = station;
    }

    /**
     * @return the station
     */
    public String getStation() {
        return station;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    public String toString() {
        return station + " " + number;
    }
}