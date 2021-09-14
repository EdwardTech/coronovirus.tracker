package io.alexia.coronovirus.tracker.models;

import java.util.Objects;

public class LocationStats {

    private String state;
    private String country;
    private int latestTotalCases;

    public int getChangesPrevDay() {
        return changesPrevDay;
    }

    public void setChangesPrevDay(int changesPrevDay) {
        this.changesPrevDay = changesPrevDay;
    }

    private int changesPrevDay;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestTotalCases() {
        return latestTotalCases;
    }

    public void setLatestTotalCases(int latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationStats that = (LocationStats) o;
        return latestTotalCases == that.latestTotalCases && Objects.equals(state, that.state) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, country, latestTotalCases);
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestTotalCases=" + latestTotalCases +
                '}';
    }
}
