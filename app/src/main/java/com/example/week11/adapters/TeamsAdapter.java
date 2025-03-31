package com.example.week11.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week11.R;
import com.example.week11.model.Team;


import java.util.List;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.TeamViewHolder> {
    private List<Team> teams;

    public TeamsAdapter(List<Team> teams) {
        this.teams = teams;
    }

    @NonNull
// In TeamsAdapter.java
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_team, parent, false);
        return new TeamViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        Team team = teams.get(position);
        holder.bind(team);
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public void updateData(List<Team> newTeams) {
        teams = newTeams;
        notifyDataSetChanged();
    }

    static class TeamViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView countryTextView;
        private TextView leagueTextView;
        private TextView stadiumTextView;
        private TextView foundedTextView;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.teamName);
            countryTextView = itemView.findViewById(R.id.teamCountry);
            leagueTextView = itemView.findViewById(R.id.teamLeague);
            stadiumTextView = itemView.findViewById(R.id.teamStadium);
            foundedTextView = itemView.findViewById(R.id.teamFounded);
        }

        public void bind(Team team) {
            nameTextView.setText(team.getName());
            countryTextView.setText(team.getCountry());
            leagueTextView.setText(team.getLeague());
            stadiumTextView.setText(team.getStadium());
            foundedTextView.setText(String.valueOf(team.getFoundedYear()));
        }
    }
}