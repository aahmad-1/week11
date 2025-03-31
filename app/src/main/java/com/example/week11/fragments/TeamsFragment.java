package com.example.week11.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import android.widget.Spinner;

import com.example.week11.R;
import com.example.week11.repositories.Repository;
import com.example.week11.repositories.SoccerApp;
import com.example.week11.adapters.TeamsAdapter;
import com.example.week11.model.Team;

public class TeamsFragment extends Fragment {
    private RecyclerView recyclerView;
    private TeamsAdapter adapter;
    private Repository<Team> teamRepository;
    private SearchView searchView;
    private Spinner sortSpinner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SoccerApp app = (SoccerApp) requireActivity().getApplication();
        teamRepository = app.getTeamRepository();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teams, container, false);

        recyclerView = view.findViewById(R.id.teamsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new TeamsAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        searchView = view.findViewById(R.id.searchView);
        sortSpinner = view.findViewById(R.id.sortSpinner);

        setupSearch();
        setupSort();

        loadTeams();

        return view;
    }

    private void loadTeams() {
        List<Team> teams = teamRepository.getAll();
        adapter.updateData(teams);
    }

    private void setupSearch() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterTeams(newText);
                return true;
            }
        });
    }

    private void filterTeams(String query) {
        List<Team> filtered = teamRepository.filter(team ->
                team.getName().toLowerCase().contains(query.toLowerCase()) ||
                        team.getCountry().toLowerCase().contains(query.toLowerCase()) ||
                        team.getLeague().toLowerCase().contains(query.toLowerCase())
        );
        adapter.updateData(filtered);
    }

    private void setupSort() {
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.team_sort_options,
                android.R.layout.simple_spinner_item
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(spinnerAdapter);

        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                sortTeams(selected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void sortTeams(String sortBy) {
        Comparator<Team> comparator;
        switch (sortBy) {
            case "Name (A-Z)":
                comparator = Comparator.comparing(Team::getName);
                break;
            case "Name (Z-A)":
                comparator = Comparator.comparing(Team::getName).reversed();
                break;
            case "Country":
                comparator = Comparator.comparing(Team::getCountry);
                break;
            case "League":
                comparator = Comparator.comparing(Team::getLeague);
                break;
            case "Founded (Oldest)":
                comparator = Comparator.comparingInt(Team::getFoundedYear);
                break;
            case "Founded (Newest)":
                comparator = Comparator.comparingInt(Team::getFoundedYear).reversed();
                break;
            default:
                comparator = Comparator.comparing(Team::getName);
        }

        List<Team> sorted = new ArrayList<>(teamRepository.getAll());
        sorted.sort(comparator);
        adapter.updateData(sorted);
    }
}
