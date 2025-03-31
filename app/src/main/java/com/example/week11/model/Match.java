package com.example.week11.model;

// Match.java
public class Match implements SoccerEntity {
    private String homeTeam, awayTeam, score, competition, date, venue;

    public Match(String homeTeam, String awayTeam, String score, String competition, String date, String venue) {
        if (homeTeam == null || homeTeam.isEmpty()) {
            throw new IllegalArgumentException("Home team cannot be null or empty");
        }
        if (awayTeam == null || awayTeam.isEmpty()) {
            throw new IllegalArgumentException("Away team cannot be null or empty");
        }
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.score = score;
        this.competition = competition;
        this.date = date;
        this.venue = venue;
    }

    @Override
    public String getId() {
        return homeTeam + " vs " + awayTeam;
    }

    @Override
    public String getName() {
        return homeTeam + " vs " + awayTeam;
    }

    // Getters and setters for all fields
    public String getHomeTeam() { return homeTeam; }
    public String getAwayTeam() { return awayTeam; }
    public String getScore() { return score; }
    public String getCompetition() { return competition; }
    public String getDate() { return date; }
    public String getVenue() { return venue; }
}