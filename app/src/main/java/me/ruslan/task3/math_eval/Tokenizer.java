package me.ruslan.task3.math_eval;

import java.util.ArrayList;
import java.util.HashMap;

public class Tokenizer {
    private static final HashMap<Character, Token> tokenTypes = new HashMap<Character, Token>() {{
        put('1', Token.DIGIT);
        put('2', Token.DIGIT);
        put('3', Token.DIGIT);
        put('4', Token.DIGIT);
        put('5', Token.DIGIT);
        put('6', Token.DIGIT);
        put('7', Token.DIGIT);
        put('8', Token.DIGIT);
        put('9', Token.DIGIT);
        put('0', Token.DIGIT);
        put('+', Token.PLUS);
        put('-', Token.MINUS);
        put('*', Token.MULTIPLY);
        put('/', Token.DIVIDE);
        put('.', Token.POINT);
    }};

    public ArrayList<Pair<Token, String>> tokenize(String expr) {
        int pos = 0;
        String currentNumber = "";
        ArrayList<Pair<Token, String>> tokens = new ArrayList<>();
        while(pos < expr.length()) {
            char ch = expr.charAt(pos);
            if(!tokenTypes.containsKey(ch)) {
                pos++;
                continue;
            }
            Token tType = tokenTypes.get(ch);

            if(tType == Token.POINT && !currentNumber.isEmpty() && currentNumber.charAt(currentNumber.length()-1) != '.')
                currentNumber += ".";
            else if(tType == Token.DIGIT)
                currentNumber += ch;
            else {
                if(!currentNumber.isEmpty()) {
                    tokens.add(new Pair<>(Token.NUMBER, currentNumber));
                    currentNumber = "";
                }
                tokens.add(new Pair<>(tType, String.valueOf(ch)));
            }

            pos++;
        }
        if(!currentNumber.isEmpty())
            tokens.add(new Pair<>(Token.NUMBER, currentNumber));

        return tokens;
    }
}
