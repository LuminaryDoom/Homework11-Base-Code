package com.example.rusili.homework11.detailscreen.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.rusili.homework11.R;
import com.example.rusili.homework11.detailscreen.api.PokemonApi;
import com.example.rusili.homework11.detailscreen.model.Pokemon;
import com.example.rusili.homework11.detailscreen.model.objects.Sprites;
import com.example.rusili.homework11.detailscreen.model.objects.Stat;
import com.example.rusili.homework11.detailscreen.model.objects.Stats;
import com.example.rusili.homework11.detailscreen.model.objects.Types;
import com.example.rusili.homework11.network.RetrofitFactory;
import com.example.rusili.homework11.pokedexActivity.api.PokedexApi;
import com.example.rusili.homework11.pokedexActivity.model.objects.PokemonEntries;

import java.util.Arrays;
import java.util.List;

public class PokemonDetailActivity extends AppCompatActivity {
    private RetrofitFactory.PokemonNetworkListener pokemonNetworkListener;
    private Context context;
    private TextView pokemon_type;
    private ImageView pokemon_img;
    private TextView string_textview;
    private TextView poke_stats;
    private String poke_name;
    private TextView nameString;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_details_itemview);
        poke_stats =  findViewById(R.id.poke_stat_textview);
        pokemon_type =  findViewById(R.id.poke_type_textview);
        string_textview = findViewById(R.id.type_string);
        pokemon_img =  findViewById(R.id.poke_images_sprites);
        nameString = findViewById(R.id.name_string);

        poke_name = getIntent().getStringExtra("Pokename");
        context = getApplicationContext();
        getPokemonDetails();
    }

    private void initialize() {

    }

    private void getPokemonDetails() {
        pokemonNetworkListener = new RetrofitFactory.PokemonNetworkListener() {

            @Override
            public void pokemonCallback(Pokemon pokemon) {
                //TODO: Display pokemon data
                //Hint: Learn how to use Glide to display an image.
                Stats[] statsArray = pokemon.getStats();
                Types[] typesArray = pokemon.getTypes();


                String value = "";
                for (int i = 0; i < statsArray.length; i++)
                    value += statsArray[i].getStat().getName() + ": " + Integer.toString(statsArray[i].getBase_stat()) + "\n";

                String types = "";
                for (int i = 0; i < typesArray.length ; i++){
                    types += typesArray[i].getType().getName()+" ";
                }

                nameString.setText(getIntent().getStringExtra("Pokename"));
                Glide.with(context)
                        .load(pokemon.getSprites().getFront_shiny())
                        .placeholder(R.drawable.pokeballicon)
                        .override(300, 300)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(pokemon_img);

                pokemon_type.setText(value);
                poke_stats.setText(types);
            }

            @Override
            public void onNetworkError(Throwable t){
                Snackbar.make(findViewById(android.R.id.content),
                        t.getMessage()
                        ,Snackbar.LENGTH_LONG).show();
            }


        };





        RetrofitFactory.getInstance().setPokemonNetworkListener(pokemonNetworkListener);
        RetrofitFactory.getInstance().getPokemon(poke_name);

    }

}
