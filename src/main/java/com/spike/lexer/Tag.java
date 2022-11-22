package com.spike.lexer;

/*
    A class to storage const tag numbers

    NUM -> 128,
    ID -> 129,
    TRUE -> 130,
    FALSE -> 131
 */
public enum Tag {

    // Numbers from 0 to 9
    NUM,

    // Identifiers  (possibly string literals ?)
    ID,

    // Boolean True
    TRUE,

    // Boolean False
    FALSE,

    // <= , >=, ==, !=
    REL_OP,

    // Some bad token
    BAD_TOKEN,
    OTHER,

    // EOF Token, maybe can be useful in future.
    EOF,

    // Binary Operator (PLUE, MINUS)
    BINARY_OP
}