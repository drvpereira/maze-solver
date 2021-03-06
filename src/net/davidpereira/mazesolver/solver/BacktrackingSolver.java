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
 * Time: 09:09
 * To change this template use File | Settings | File Templates.
 */
public class BacktrackingSolver implements MazeSolver {

    private Path path = new Path();
    private boolean foundSolution;

    @Override
    public Solution solve(Maze maze) {
        Solution solution = Solution.startRecording(maze);
        solve(maze.getStart(), maze.getGoal());
        return solution.endRecording(this);
    }

    private void solve(Position start, Position destination) {
        if (!start.equals(destination) && !foundSolution) {

            if (start.isAvailable() && !path.contains(start)) {
                path.add(start);
                for (Position child : start.getNeighbors()) {
                    solve(child, destination);
                }
            }

        } else if (start.equals(destination)) {
            path.add(start);
            foundSolution = true;
        }
    }

    @Override
    public Path getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "Depth-first Search";
    }
}
