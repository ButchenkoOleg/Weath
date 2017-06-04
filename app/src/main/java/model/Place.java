package model;

/**
 * Created by oleg on 01.06.17.
 */

public class Place {
    private float lon;
    private float lat;
    private long sanset;
    private long  sanrice;
    private String  country;
    private String  city;
    private long lastupdate;

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public long getSanset() {
        return sanset;
    }

    public void setSanset(long sanset) {
        this.sanset = sanset;
    }

    public long getSanrice() {
        return sanrice;
    }

    public void setSanrice(long sanrice) {
        this.sanrice = sanrice;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(long lastupdate) {
        this.lastupdate = lastupdate;
    }
}
