package com.example.exprweb2006.beans;

import com.example.exprweb2006.tables.Expression;
import lombok.Data;

import java.util.List;

@Data
public class ExpressionBean {
    private String message = "";
    private String current = "";
    private Expression edited;

    private List<Expression> expressions;
}
