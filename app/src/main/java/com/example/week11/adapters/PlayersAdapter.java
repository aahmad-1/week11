package com.example.week11.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week11.R;
import com.example.week11.model.Player;

import java.util.List;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder> {
    private List<Player> players;

    public PlayersAdapter(List<Player> players) {
        this.players = players;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_players, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Player player = players.get(position);
        holder.bind(player);
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public void updateData(List<Player> newPlayers) {
        players = newPlayers;
        notifyDataSetChanged();
    }

    static class PlayerViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView ageTextView;
        private TextView nationalityTextView;
        private TextView positionTextView;
        private TextView teamTextView;
        private TextView jerseyTextView;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.playerName);
            ageTextView = itemView.findViewById(R.id.playerAge);
            nationalityTextView = itemView.findViewById(R.id.playerNationality);
            positionTextView = itemView.findViewById(R.id.playerPosition);
            teamTextView = itemView.findViewById(R.id.playerTeam);
            jerseyTextView = itemView.findViewById(R.id.playerJersey);
        }

        public void bind(Player player) {
            nameTextView.setText(player.getName());
            ageTextView.setText("Age: " + String.valueOf(player.getAge()));
            nationalityTextView.setText("From: " + player.getNationality());
            positionTextView.setText("Position: " + player.getPosition());
            teamTextView.setText("Team: " + player.getTeam());
            jerseyTextView.setText("Jersey number: " + String.valueOf(player.getJerseyNumber()));
        }
    }
}