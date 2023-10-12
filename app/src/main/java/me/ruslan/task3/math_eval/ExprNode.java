package me.ruslan.task3.math_eval;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExprNode {
    Token token;
    String value;
    ExprNode left, right;

    ExprNode(Token token, String value) {
        this.token = token;
        this.value = value;
        this.left = this.right = null;
    }

    public String toString() {
        return String.format("TreeNode(value=%s, left=%s, right=%s)", value, left, right);
    }

    private BigDecimal lZero() {
        return left == null ? new BigDecimal(0) : left.eval();
    }

    private BigDecimal rZero() {
        return right == null ? new BigDecimal(0) : right.eval();
    }

    public BigDecimal eval() {
        switch (token) {
            case NUMBER:
                return new BigDecimal(value);
            case PLUS:
                return lZero().add(rZero());
            case MINUS:
                return lZero().subtract(rZero());
            case MULTIPLY:
                return lZero().multiply(rZero());
            case DIVIDE:
                return lZero().divide(rZero(), 4, RoundingMode.HALF_UP);
            default:
                return new BigDecimal(0);
        }
    }
}
