package com.example.rusili.homework11.util;

/**
 * Created by rusi.li on 11/22/17.
 */

public enum  Host {
    PokeAPI("http://pokeapi.co/api/v2/");

    private final String url;

    private Host(final String url){
        this.url = url;
    }

    public String getUrl(){
        return this.url;
    }
}