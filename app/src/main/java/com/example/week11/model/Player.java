package com.example.week11.model;

public class Player implements SoccerEntity {
    private String name, nationality, position, team;
    private int age, jerseyNumber;

    public Player(String name, int age, String nationality, String position, String team, int jerseyNumber) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The player name can't be null or empty");
        }
        if (age <= 0) {
            throw new IllegalArgumentException("The player age must be positive");
        }
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        this.position = position;
        this.team = team;
        this.jerseyNumber = jerseyNumber;
    }

    @Override
    public String getId() {
        return name;
    }

    @Override
    public String getName() {
        return name;
    }

    // Getters
    public int getAge() { return age; }
    public String getNationality() {
        return nationality; }

    public String getPosition() {
        return position; }

    public String getTeam() {
        return team; }

    public int getJerseyNumber() {
        return jerseyNumber; }
}