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
package net.davidpereira.mazesolver.solver;

import net.davidpereira.mazesolver.domain.Maze;
import net.davidpereira.mazesolver.domain.Path;
import net.davidpereira.mazesolver.domain.Position;
import net.davidpereira.mazesolver.domain.Solution;

/**
 * Created with IntelliJ IDEA.
 * User: U403
 * Date: 21/05/14
 * Time: 08:49
 * To change this template use File | Settings | File Templates.
 */
public class WallFollowerSolver implements MazeSolver {

    private char direction = 'N';
    private Path path = new Path();

    @Override
    public Solution solve(Maze maze) {
        Solution solution = Solution.startRecording(maze);

        Position current = maze.getStart();

        while(!current.equals(maze.getGoal())) {
            path.add(current);

            switch(direction) {
                case 'N':

                    if (current.getEast().isAvailable()) {
                        direction = 'E';
                        current = current.getEast();
                    } else if (current.getNorth().isAvailable()) {
                        direction = 'N';
                        current = current.getNorth();
                    } else if (current.getWest().isAvailable()) {
                        direction = 'W';
                        current = current.getWest();
                    } else {
                        direction = 'S';
                        current = current.getSouth();
                    }

                    break;
                case 'S':

                    if (current.getWest().isAvailable()) {
                        direction = 'W';
                        current = current.getWest();
                    } else if (current.getSouth().isAvailable()) {
                        direction = 'S';
                        current = current.getSouth();
                    } else if (current.getEast().isAvailable()) {
                        direction = 'E';
                        current = current.getEast();
                    } else {
                        direction = 'N';
                        current = current.getNorth();
                    }

                    break;
                case 'E':

                    if (current.getSouth().isAvailable()) {
                        direction = 'S';
                        current = current.getSouth();
                    } else if (current.getEast().isAvailable()) {
                        direction = 'E';
                        current = current.getEast();
                    } else if (current.getNorth().isAvailable()) {
                        direction = 'N';
                        current = current.getNorth();
                    } else {
                        direction = 'W';
                        current = current.getWest();
                    }

                    break;
                case 'W':

                    if (current.getNorth().isAvailable()) {
                        direction = 'N';
                        current = current.getNorth();
                    } else if (current.getWest().isAvailable()) {
                        direction = 'W';
                        current = current.getWest();
                    } else if (current.getSouth().isAvailable()) {
                        direction = 'S';
                        current = current.getSouth();
                    } else {
                        direction = 'E';
                        current = current.getEast();
                    }

                    break;
            }

        }

        path.add(current);
        return solution.endRecording(this);
    }

    @Override
    public Path getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "Wall Follower";
    }

}
