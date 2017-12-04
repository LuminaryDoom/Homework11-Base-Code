package com.example.rusili.homework11.pokedexActivity.view;

import android.app.ActionBar;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;


import com.example.rusili.homework11.R;
import com.example.rusili.homework11.network.RetrofitFactory;
import com.example.rusili.homework11.pokedexActivity.model.Pokedex;
import com.example.rusili.homework11.pokedexActivity.model.objects.PokemonEntries;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rusi.li on 11/22/17.
 */

public class PokedexFragment extends Fragment {
    private RetrofitFactory.PokedexNetworkListener pokedexNetworkListener;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokedex, container, false);

        recyclerView = view.findViewById(R.id.pokedex_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getPokedexList();

        fab = view.findViewById(R.id.fab);
        setFabButton();


        return view;


    }


    public void setFabButton() {

        fab.setVisibility(View.GONE);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.smoothScrollToPosition(0);
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    fab.setVisibility(View.VISIBLE);
                }
                if (dy <= 0) {

                    if (true)
                        fab.setVisibility(View.GONE);

                }
            }

        });
    }


    private void getPokedexList() {
        pokedexNetworkListener = new RetrofitFactory.PokedexNetworkListener() {
            @Override
            public void pokedexCallback(Pokedex pokedex) {
                // TODO: show Pokemon
                // Each pokemon is in the Pokemon_Species object.
                List<PokemonEntries> pokemon_species = Arrays.asList(pokedex.getPokemon_entries());

                PokedexAdapter pokedexAdapter = new PokedexAdapter(pokemon_species, getContext());

                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 6);
                    recyclerView.setAdapter(pokedexAdapter);
                    recyclerView.setLayoutManager(gridLayoutManager);
                } else {
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
                    recyclerView.setAdapter(pokedexAdapter);
                    recyclerView.setLayoutManager(gridLayoutManager);
                }
    }

    @Override
    public void onNetworkError(Throwable t) {
        Snackbar.make(recyclerView.findViewById(android.R.id.content),
                t.getMessage(),
                Snackbar.LENGTH_LONG).show();

    }
};


        RetrofitFactory.getInstance().setPokedexListener(pokedexNetworkListener);
                RetrofitFactory.getInstance().getPokedex(2);

                }
                }
