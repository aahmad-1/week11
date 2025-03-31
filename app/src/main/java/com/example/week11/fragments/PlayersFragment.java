package com.example.week11.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week11.R;
import com.example.week11.repositories.Repository;
import com.example.week11.repositories.SoccerApp;
import com.example.week11.adapters.PlayersAdapter;
import com.example.week11.model.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlayersFragment extends Fragment {
    private RecyclerView recyclerView;
    private PlayersAdapter adapter;
    private Repository<Player> playerRepository;
    private SearchView searchView;
    private Spinner sortSpinner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SoccerApp app = (SoccerApp) requireActivity().getApplication();
        playerRepository = app.getPlayerRepository();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_players, container, false);

        recyclerView = view.findViewById(R.id.playersRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new PlayersAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        searchView = view.findViewById(R.id.searchView);
        sortSpinner = view.findViewById(R.id.sortSpinner);

        setupSearch();
        setupSort();

        loadPlayers();

        return view;
    }

    private void loadPlayers() {
        List<Player> players = playerRepository.getAll();
        adapter.updateData(players);
    }

    private void setupSearch() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterPlayers(newText);
                return true;
            }
        });
    }

    private void filterPlayers(String query) {
        List<Player> filtered = playerRepository.filter(player ->
                player.getName().toLowerCase().contains(query.toLowerCase()) ||
                        player.getTeam().toLowerCase().contains(query.toLowerCase()) ||
                        player.getPosition().toLowerCase().contains(query.toLowerCase())
        );
        adapter.updateData(filtered);
    }

    private void setupSort() {
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.player_sort_options,
                android.R.layout.simple_spinner_item
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(spinnerAdapter);

        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                sortPlayers(selected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void sortPlayers(String sortBy) {
        Comparator<Player> comparator;
        switch (sortBy) {
            case "Name (A-Z)":
                comparator = Comparator.comparing(Player::getName);
                break;
            case "Name (Z-A)":
                comparator = Comparator.comparing(Player::getName).reversed();
                break;
            case "Age (Youngest)":
                comparator = Comparator.comparingInt(Player::getAge);
                break;
            case "Age (Oldest)":
                comparator = Comparator.comparingInt(Player::getAge).reversed();
                break;
            case "Position":
                comparator = Comparator.comparing(Player::getPosition);
                break;
            case "Team":
                comparator = Comparator.comparing(Player::getTeam);
                break;
            default:
                comparator = Comparator.comparing(Player::getName);
        }

        List<Player> sorted = new ArrayList<>(playerRepository.getAll());
        sorted.sort(comparator);
        adapter.updateData(sorted);
    }
}