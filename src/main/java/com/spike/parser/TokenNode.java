package com.spike.parser;

import com.spike.lexer.Token;

import java.util.List;

public abstract class TokenNode {
    public abstract Token getToken();
    public abstract List<TokenNode> getChildren();
}