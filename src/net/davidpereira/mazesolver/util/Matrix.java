/*
 * Copyright 2014 David Pereira
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.davidpereira.mazesolver.util;

import net.davidpereira.mazesolver.domain.Position;
import net.davidpereira.mazesolver.exceptions.MatrixIndexOutOfBoundsException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: drvpereira
 * Date: 30/04/14
 */
public class Matrix implements Serializable {

    private int numRows;
    private int numCols;
    private char[][] data;

    public Matrix(int numRows, int numCols) {
        data = new char[numRows][numCols];
        this.numCols = numCols;
        this.numRows = numRows;
    }

    public void set(int row, int col, char value) {
        if (row < 0) throw new MatrixIndexOutOfBoundsException("The row number cannot be less than zero.");
        if (col < 0) throw new MatrixIndexOutOfBoundsException("The column number cannot be less than zero.");
        if (row >= numRows) throw new MatrixIndexOutOfBoundsException("The row number cannot be greater than " + (numRows-1) + " (starts at 0).");
        if (col >= numCols) throw new MatrixIndexOutOfBoundsException("The column number cannot be greater than " + (numCols-1) + " (starts at 0).");

        data[row][col] = value;
    }

    public Matrix deleteRow(int rowNum) {
        Matrix newMatrix = new Matrix(numRows-1, numCols);
        int newRow = 0;

        for (int r = 0; r < numRows; r++) {

            if (r == rowNum) continue;

            for (int c = 0; c < numCols; c++) {
                newMatrix.set(newRow, c, data[r][c]);
            }

            newRow++;
        }

        return newMatrix;
    }

    public Matrix deleteCol(int colNum) {
        Matrix newMatrix = new Matrix(numRows, numCols-1);

        for (int r = 0; r < numRows; r++) {
            int newCol = 0;

            for (int c = 0; c < numCols; c++) {
                if (c == colNum) continue;

                newMatrix.set(r, newCol++, data[r][c]);
            }
        }

        return newMatrix;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                sb.append(data[i][j]);
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public int getRowCount() {
        return numRows;
    }

    public List<Character> getRow(int rowNum) {
        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < data[rowNum].length; i++) {
            chars.add(data[rowNum][i]);
        }

        return chars;
    }

    public List<Character> getCol(int colNum) {
        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            chars.add(data[i][colNum]);
        }

        return chars;
    }

    public int getColCount() {
        return numCols;
    }

    public char get(int r, int c) {
        if (r < 0) throw new MatrixIndexOutOfBoundsException("The row number cannot be less than zero.");
        if (c < 0) throw new MatrixIndexOutOfBoundsException("The column number cannot be less than zero.");
        if (r >= numRows) throw new MatrixIndexOutOfBoundsException("The row number cannot be greater than " + (numRows-1) + " (starts at 0).");
        if (c >= numCols) throw new MatrixIndexOutOfBoundsException("The column number cannot be greater than " + (numCols-1) + " (starts at 0).");

        return data[r][c];
    }

    @Override
    public boolean equals(Object obj) {
        Matrix m = (Matrix) obj;

        if (this.numRows == m.numRows && this.numCols == m.numCols) {
            boolean sameElements = true;
            for (int i = 0; i < numRows; i++) {
                sameElements &= this.getRow(i).equals(m.getRow(i));
            }
            return sameElements;
        } else {
            return false;
        }
    }

}
