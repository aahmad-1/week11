package com.example.week11.repos;

import android.app.Application;

import com.example.week11.DataProvider;
import com.example.week11.model.Match;
import com.example.week11.model.Team;
import com.example.week11.model.Player;

public class SoccerApp extends Application {
    private Repository<Team> teamRepository;
    private Repository<Player> playerRepository;
    private Repository<Match> matchRepository;

    @Override
    public void onCreate() {
        super.onCreate();

        teamRepository = new Repository<>();
        playerRepository = new Repository<>();
        matchRepository = new Repository<>();

        // Initialize with sample data
        for (Team team : DataProvider.createSampleTeams()) {
            teamRepository.add(team);
        }

        for (Player player : DataProvider.createSamplePlayers()) {
            playerRepository.add(player);
        }

        for (Match match : DataProvider.createSampleMatches()) {
            matchRepository.add(match);
        }
    }

    public Repository<Team> getTeamRepository() {
        return teamRepository;
    }

    public Repository<Player> getPlayerRepository() {
        return playerRepository;
    }

    public Repository<Match> getMatchRepository() {
        return matchRepository;
    }
}