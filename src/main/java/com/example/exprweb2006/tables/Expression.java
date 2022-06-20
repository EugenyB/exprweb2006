package com.example.exprweb2006.tables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expression {
    private int id;
    private String expr;
    private double value;
}
