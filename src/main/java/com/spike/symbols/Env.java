package com.spike.symbols;

import java.util.Hashtable;

/*
    Env is the collection of symbol tables

 */
public class Env {
    private Hashtable<String, String> hashtable;
    protected Env prev;

    public Env(Env prevEnv) {
        hashtable = new Hashtable<>();
        prev = prevEnv;
    }

    //public void put(String s, Symbol )
}