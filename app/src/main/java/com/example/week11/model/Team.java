package com.example.week11.model;

// Team.java
public class Team implements SoccerEntity {
    private String name, country, league, stadium;
    private int foundedYear;

    public Team(String name, String country, String league, String stadium, int foundedYear) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The team name can't be null or empty");
        }
        this.name = name;
        this.country = country;
        this.league = league;
        this.stadium = stadium;
        this.foundedYear = foundedYear;
    }
    @Override
    public String getId() {
        return name;
    }

    @Override
    public String getName() {
        return name;
    }

    // Getters and setters for all fields
    public String getCountry() {
        return country; }
    public String getLeague() {
        return league; }
    public String getStadium() {
        return stadium; }
    public int getFoundedYear() {
        return foundedYear; }
}