package net.davidpereira.mazesolver.util;

import net.davidpereira.mazesolver.exceptions.MatrixIndexOutOfBoundsException;
import net.davidpereira.mazesolver.util.Matrix;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: drvpereira
 * Date: 01/05/14
 */
public class MatrixTest {

    @Test
    public void constructorArgsShouldSetMatrixSize() {
        Matrix m = new Matrix(3, 4);
        Assert.assertEquals(3, m.getRowCount());
        Assert.assertEquals(4, m.getColCount());
    }

    @Test
    public void setShouldPutValuesInMatrixAndGetShouldReturnThem() {
        Matrix m = new Matrix(2, 2);
        m.set(0, 0, 'a');
        m.set(0, 1, 'b');
        m.set(1, 0, 'c');
        m.set(1, 1, 'd');

        Assert.assertEquals('a', m.get(0, 0));
        Assert.assertEquals('b', m.get(0, 1));
        Assert.assertEquals('c', m.get(1, 0));
        Assert.assertEquals('d', m.get(1, 1));
    }

    @Test
    public void rowsAndColsShouldNotBeGreaterThanMatrixSize() {
        Matrix m = new Matrix(2, 3);

        try {
            m.set(2, 0, 'a');
            Assert.fail("Should have failed since selected row is greater than number of rows.");
        } catch(MatrixIndexOutOfBoundsException e) {
            Assert.assertEquals("The row number cannot be greater than 1 (starts at 0).", e.getMessage());
        }

        try {
            m.set(0, 3, 'a');
            Assert.fail("Should have failed since selected column is greater than number of columns.");
        } catch(MatrixIndexOutOfBoundsException e) {
            Assert.assertEquals("The column number cannot be greater than 2 (starts at 0).", e.getMessage());
        }

        try {
            m.get(2, 0);
            Assert.fail("Should have failed since selected row is greater than number of rows.");
        } catch(MatrixIndexOutOfBoundsException e) {
            Assert.assertEquals("The row number cannot be greater than 1 (starts at 0).", e.getMessage());
        }

        try {
            m.get(0, 3);
            Assert.fail("Should have failed since selected column is greater than number of columns.");
        } catch(MatrixIndexOutOfBoundsException e) {
            Assert.assertEquals("The column number cannot be greater than 2 (starts at 0).", e.getMessage());
        }
    }

    @Test
    public void rowsAndColsShouldNotBeLessThanZero() {
        Matrix m = new Matrix(2, 2);

        try {
            m.set(-1, 0, 'a');
            Assert.fail("Should have failed since selected row is less than zero.");
        } catch(MatrixIndexOutOfBoundsException e) {
            Assert.assertEquals("The row number cannot be less than zero.", e.getMessage());
        }

        try {
            m.set(0, -1, 'a');
            Assert.fail("Should have failed since selected column is less than zero.");
        } catch(MatrixIndexOutOfBoundsException e) {
            Assert.assertEquals("The column number cannot be less than zero.", e.getMessage());
        }

        try {
            m.get(-1, 0);
            Assert.fail("Should have failed since selected row is less than zero.");
        } catch(MatrixIndexOutOfBoundsException e) {
            Assert.assertEquals("The row number cannot be less than zero.", e.getMessage());
        }

        try {
            m.get(0, -1);
            Assert.fail("Should have failed since selected column is less than zero.");
        } catch(MatrixIndexOutOfBoundsException e) {
            Assert.assertEquals("The column number cannot be less than zero.", e.getMessage());
        }
    }

    @Test
    public void shouldBeAbleToGetAllElementsInARow() {
        Matrix m = new Matrix(2, 3);
        m.set(0, 0, 'a');
        m.set(0, 1, 'b');
        m.set(0, 2, 'c');
        m.set(1, 0, 'd');
        m.set(1, 1, 'e');
        m.set(1, 2, 'f');

        Assert.assertEquals(Arrays.asList('a', 'b', 'c'), m.getRow(0));
        Assert.assertEquals(Arrays.asList('d', 'e', 'f'), m.getRow(1));
    }

    @Test
    public void shouldBeAbleToGetAllElementsInAColumn() {
        Matrix m = new Matrix(2, 3);
        m.set(0, 0, 'a');
        m.set(0, 1, 'b');
        m.set(0, 2, 'c');
        m.set(1, 0, 'd');
        m.set(1, 1, 'e');
        m.set(1, 2, 'f');

        Assert.assertEquals(Arrays.asList('a', 'd'), m.getCol(0));
        Assert.assertEquals(Arrays.asList('b', 'e'), m.getCol(1));
        Assert.assertEquals(Arrays.asList('c', 'f'), m.getCol(2));
    }

    @Test
    public void deleteRowReturnsMatrixWithoutTheInformedRow() {
        Matrix m1 = new Matrix(2, 3);
        m1.set(0, 0, 'a');
        m1.set(0, 1, 'b');
        m1.set(0, 2, 'c');
        m1.set(1, 0, 'd');
        m1.set(1, 1, 'e');
        m1.set(1, 2, 'f');

        Matrix m2 = new Matrix(1, 3);
        m2.set(0, 0, 'd');
        m2.set(0, 1, 'e');
        m2.set(0, 2, 'f');

        Assert.assertEquals(m2, m1.deleteRow(0));
    }

    @Test
    public void deleteColReturnsMatrixWithoutTheInformedColumn() {
        Matrix m1 = new Matrix(2, 3);
        m1.set(0, 0, 'a');
        m1.set(0, 1, 'b');
        m1.set(0, 2, 'c');
        m1.set(1, 0, 'd');
        m1.set(1, 1, 'e');
        m1.set(1, 2, 'f');

        Matrix m2 = new Matrix(2, 2);
        m2.set(0, 0, 'b');
        m2.set(0, 1, 'c');
        m2.set(1, 0, 'e');
        m2.set(1, 1, 'f');

        Assert.assertEquals(m2, m1.deleteCol(0));
    }

    @Test
    public void theMatrixShouldHaveAStringRepresentation() {
        Matrix m1 = new Matrix(2, 3);
        m1.set(0, 0, 'a');
        m1.set(0, 1, 'b');
        m1.set(0, 2, 'c');
        m1.set(1, 0, 'd');
        m1.set(1, 1, 'e');
        m1.set(1, 2, 'f');

        Assert.assertEquals("abc\ndef\n", m1.toString());
    }

    @Test
    public void differentMatricesAreDifferent() {
        Matrix m1 = new Matrix(2, 3);
        m1.set(0, 0, 'a');
        m1.set(0, 1, 'b');
        m1.set(0, 2, 'c');
        m1.set(1, 0, 'd');
        m1.set(1, 1, 'e');
        m1.set(1, 2, 'f');

        Matrix m2 = new Matrix(2, 3);
        m2.set(0, 0, 'a');
        m2.set(0, 1, 'b');
        m2.set(0, 2, 'c');
        m2.set(1, 0, 'd');
        m2.set(1, 1, 'e');
        m2.set(1, 2, 'f');

        Assert.assertEquals(m2, m1);

        m1 = new Matrix(2, 3);
        m1.set(0, 0, 'a');
        m1.set(0, 1, 'b');
        m1.set(0, 2, 'c');
        m1.set(1, 0, 'd');
        m1.set(1, 1, 'e');
        m1.set(1, 2, 'f');

        m2 = new Matrix(2, 3);
        m2.set(0, 0, 'a');
        m2.set(0, 1, 'b');
        m2.set(0, 2, 'c');
        m2.set(1, 0, 'd');
        m2.set(1, 1, 'e');
        m2.set(1, 2, 'g');

        Assert.assertFalse(m2.equals(m1));

        m1 = new Matrix(1, 2);
        m2 = new Matrix(2, 1);
        Assert.assertFalse(m2.equals(m1));

    }


}
