package com.spike.parser.expressions;

import com.spike.exceptions.UnSupportedOperatorException;
import com.spike.parser.TokenNode;

public abstract class Expression extends TokenNode {
    public abstract Object evaluate() throws UnSupportedOperatorException;
}