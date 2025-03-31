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
import com.example.week11.data_management.Repository;
import com.example.week11.data_management.SoccerApp;
import com.example.week11.adapters.MatchesAdapter;
import com.example.week11.model.Match;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MatchesFragment extends Fragment {
    private RecyclerView recyclerView;
    private MatchesAdapter adapter;
    private Repository<Match> matchRepository;
    private SearchView searchView;
    private Spinner sortSpinner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SoccerApp app = (SoccerApp) requireActivity().getApplication();
        matchRepository = app.getMatchRepository();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_matches, container, false);

        recyclerView = view.findViewById(R.id.matchesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new MatchesAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        searchView = view.findViewById(R.id.searchView);
        sortSpinner = view.findViewById(R.id.sortSpinner);

        setupSearch();
        setupSort();

        loadMatches();

        return view;
    }

    private void loadMatches() {
        List<Match> matches = matchRepository.getAll();
        adapter.updateData(matches);
    }

    private void setupSearch() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterMatches(newText);
                return true;
            }
        });
    }

    private void filterMatches(String query) {
        List<Match> filtered = matchRepository.filter(match ->
                match.getHomeTeam().toLowerCase().contains(query.toLowerCase()) ||
                        match.getAwayTeam().toLowerCase().contains(query.toLowerCase()) ||
                        match.getCompetition().toLowerCase().contains(query.toLowerCase()) ||
                        match.getStadium().toLowerCase().contains(query.toLowerCase())
        );
        adapter.updateData(filtered);
    }

    private void setupSort() {
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.match_sort_options,
                android.R.layout.simple_spinner_item
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(spinnerAdapter);

        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                sortMatches(selected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void sortMatches(String sortBy) {
        Comparator<Match> comparator;
        switch (sortBy) {
            case "Date (Recent)":
                comparator = (m1, m2) -> m2.getDate().compareTo(m1.getDate());
                break;
            case "Date (Oldest)":
                comparator = Comparator.comparing(Match::getDate);
                break;
            case "Home Team":
                comparator = Comparator.comparing(Match::getHomeTeam);
                break;
            case "Away Team":
                comparator = Comparator.comparing(Match::getAwayTeam);
                break;
            case "Competition":
                comparator = Comparator.comparing(Match::getCompetition);
                break;
            default:
                comparator = (m1, m2) -> m2.getDate().compareTo(m1.getDate());
        }

        List<Match> sorted = new ArrayList<>(matchRepository.getAll());
        sorted.sort(comparator);
        adapter.updateData(sorted);
    }
}