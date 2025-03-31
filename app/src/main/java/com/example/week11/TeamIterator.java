package com.example.week11;

import java.util.Iterator;
import java.util.List;
import com.example.week11.model.Team;

public class TeamIterator implements Iterator<Team> {
    private List<Team> teams;
    private int position = 0;

    public TeamIterator(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public boolean hasNext() {
        return position < teams.size();
    }

    @Override
    public Team next() {
        return teams.get(position++);
    }
}