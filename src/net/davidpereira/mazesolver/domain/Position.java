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
package net.davidpereira.mazesolver.domain;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: U403
 * Date: 21/05/14
 * Time: 08:46
 * To change this template use File | Settings | File Templates.
 */
public class Position implements Serializable {

    private int row;
    private int column;
    private boolean available;

    private Position north;
    private Position south;
    private Position east;
    private Position west;

    public Position(int row, int column, boolean available) {
        this.row = row;
        this.column = column;
        this.available = available;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public boolean isAvailable() {
        return available;
    }

    public Position getEast() {
        return east;
    }

    public Position getNorth() {
        return north;
    }

    public Position getWest() {
        return west;
    }

    public Position getSouth() {
        return south;
    }

    public Position[] getNeighbors() {
        if (available)
            return new Position[] { north, east, west, south };
        else
            return new Position[] { };
    }

    public int getManhattanDistance(Position other) {
        return Math.abs(row - other.row) + Math.abs(column - other.column);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (column != position.column) return false;
        if (row != position.row) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }


    public void setNorth(Position north) {
        this.north = north;
    }

    public void setSouth(Position south) {
        this.south = south;
    }

    public void setEast(Position east) {
        this.east = east;
    }

    public void setWest(Position west) {
        this.west = west;
    }
}
