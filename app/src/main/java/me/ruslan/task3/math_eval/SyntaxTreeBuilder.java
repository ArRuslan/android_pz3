package me.ruslan.task3.math_eval;

import java.util.List;

public class SyntaxTreeBuilder {
    private int index = 0;
    private final List<Pair<Token, String>> tokenValuePairs;

    public SyntaxTreeBuilder(List<Pair<Token, String>> tokenValuePairs) {
        this.tokenValuePairs = tokenValuePairs;
    }

    public ExprNode build() {
        return parse(false);
    }

    private ExprNode parse(boolean term) {
        ExprNode left = term ? parseFactor() : parse(true);

        while (index < tokenValuePairs.size()) {
            Pair<Token, String> pair = tokenValuePairs.get(index);
            Token token = pair.getKey();

            boolean cond = term ? token == Token.MULTIPLY || token == Token.DIVIDE : token == Token.PLUS || token == Token.MINUS;
            if (cond) {
                index++;
                ExprNode right = term ? parseFactor() : parse(true);

                ExprNode binaryNode = new ExprNode(token, pair.getValue());
                binaryNode.left = left;
                binaryNode.right = right;

                left = binaryNode;
            } else {
                break;
            }
        }

        return left;
    }

    private ExprNode parseFactor() {
        Pair<Token, String> pair = tokenValuePairs.get(index++);
        return new ExprNode(pair.getKey(), pair.getValue());

    }
}
