package com.example.rusili.homework11.pokedexActivity.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.rusili.homework11.R;
import com.example.rusili.homework11.detailscreen.model.Pokemon;
import com.example.rusili.homework11.detailscreen.view.PokemonDetailActivity;
import com.example.rusili.homework11.pokedexActivity.api.PokedexApi;
import com.example.rusili.homework11.pokedexActivity.model.Pokedex;
import com.example.rusili.homework11.pokedexActivity.model.objects.PokemonEntries;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by c4q on 12/1/17.
 */

public class PokedexViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Context context;


    private ImageView pokeimage;
    private TextView pokename;
    private TextView pokeID;
    private Button pokestats;


    public PokedexViewHolder(final View itemView) {
        super(itemView);

        context = itemView.getContext();

        pokename = (TextView) itemView.findViewById(R.id.poke_name);
        pokeID = (TextView) itemView.findViewById(R.id.poke_number);
        ImageView pokeImage = pokeimage = (ImageView) itemView.findViewById(R.id.poke_image);
        pokestats = (Button) itemView.findViewById(R.id.poke_stat);
        pokestats.setOnClickListener(this);

    }


    public void bind(PokemonEntries pokemonEntries) {

        pokename.setText(pokemonEntries.getPokemon_species().getName());
        pokeID.setText(String.valueOf(pokemonEntries.getEntry_number()));


        String urlPt1 = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";
        String urlPt2 = ".png";
        String url = urlPt1 + pokemonEntries.getEntry_number() + urlPt2;


        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.pokeballicon)
                .override(50, 50)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(pokeimage);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(context, PokemonDetailActivity.class);
        String sendName = pokename.getText().toString();
        i.putExtra("Pokename" , sendName);
        context.startActivity(i);
    }
}




