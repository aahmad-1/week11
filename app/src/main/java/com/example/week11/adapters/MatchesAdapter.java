package com.example.week11.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week11.R;
import com.example.week11.model.Match;

import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.MatchViewHolder> {
    private List<Match> matches;

    public MatchesAdapter(List<Match> matches) {
        this.matches = matches;
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_match, parent, false);
        return new MatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        Match match = matches.get(position);
        holder.bind(match);
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public void updateData(List<Match> newMatches) {
        matches = newMatches;
        notifyDataSetChanged();
    }

    static class MatchViewHolder extends RecyclerView.ViewHolder {
        private TextView teamsTextView;
        private TextView scoreTextView;
        private TextView competitionTextView;
        private TextView dateTextView;
        private TextView venueTextView;

        public MatchViewHolder(@NonNull View itemView) {
            super(itemView);
            teamsTextView = itemView.findViewById(R.id.matchTeams);
            scoreTextView = itemView.findViewById(R.id.matchScore);
            competitionTextView = itemView.findViewById(R.id.matchCompetition);
            dateTextView = itemView.findViewById(R.id.matchDate);
            venueTextView = itemView.findViewById(R.id.matchVenue);
        }

        public void bind(Match match) {
            teamsTextView.setText(match.getHomeTeam() + " vs " + match.getAwayTeam());
            scoreTextView.setText("Score: " + match.getScore());
            competitionTextView.setText("Tournament: " + match.getCompetition());
            dateTextView.setText("Date: " + match.getDate());
            venueTextView.setText("Stadium: " + match.getStadium());
        }
    }
}