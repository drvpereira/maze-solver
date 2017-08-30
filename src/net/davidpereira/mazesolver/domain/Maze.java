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
 * User: drvpereira
 * Date: 30/04/14
 */
public class Maze implements Serializable {

    private Position[][] structure;

    private Position start;
    private Position goal;

    public Maze(Position[][] structure) {
        this.structure = structure;
    }

    public void setStart(Position start) {
        this.start = start;
    }

    public void setGoal(Position goal) {
        this.goal = goal;
    }

    public Position getStart() {
        return start;
    }

    public Position getGoal() {
        return goal;
    }

    public Position getPosition(int row, int column) {
        try {
            return structure[row][column];
        } catch(ArrayIndexOutOfBoundsException e) {
            return new Position(row, column, false);
        }
    }

    public int getHeight() {
        return structure.length;
    }

    public int getWidth() {
        return structure[0].length;
    }

    public boolean areControlPositionsDefined() {
        return start != null && goal != null;
    }

    public boolean isPositionAvailable(int r, int c) {
        return getPosition(r, c).isAvailable();
    }

}
