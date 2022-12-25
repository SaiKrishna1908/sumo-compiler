package com.spike.symbols;

/*
    Env is the collection of symbol tables

 */
public class Env {    
    protected Env prev;

    public Env(Env prevEnv) {        
        prev = prevEnv;
    }

    //public void put(String s, Symbol )
}