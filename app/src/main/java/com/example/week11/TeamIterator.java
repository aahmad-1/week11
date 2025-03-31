package com.example.week11;

import java.util.List;
import com.example.week11.model.Team;

public class TeamIterator implements CustomIterator<Team> {
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
        if (!hasNext()) {
            throw new IndexOutOfBoundsException("No more teams to iterate");
        }
        return teams.get(position++);
    }
}