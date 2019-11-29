package com.project.math.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Matrix {
    private int rows;
    private int columns;
    private boolean[][] values;

    public Matrix(int rows, int columns, boolean[][] values) {
        this.rows = rows;
        this.columns = columns;
        this.values = values;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{\n");
        for (int i = 0; i < rows; i++) {
            sb.append("{");
            for (int j = 0; j < columns; j++)
                sb.append(getIntFromBoolean(values[i][j]) + " ");
            sb.append("},\n");
        }
        sb.append("}\n");

        return sb.toString();
    }

    private int getIntFromBoolean(boolean bool) {
        return bool ? 1 : 0;
    }
}
