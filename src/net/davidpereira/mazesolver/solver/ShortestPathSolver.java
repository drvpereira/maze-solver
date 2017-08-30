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

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: U403
 * Date: 21/05/14
 * Time: 09:10
 * To change this template use File | Settings | File Templates.
 */
public class ShortestPathSolver implements MazeSolver {

    private Set<Position> open = new HashSet<>();
    private Set<Position> closed = new HashSet<>();

    private Map<Position, Integer> gScore = new HashMap<>();
    private Map<Position, Integer> fScore = new HashMap<>();

    private Map<Position, Position> parenthood = new HashMap<>();

    private Path path = new Path();
    private boolean profile = true;

    public ShortestPathSolver() { }
    public ShortestPathSolver(boolean profile) {
        this.profile = profile;
    }

    @Override
    public Solution solve(Maze maze) {
        Solution solution = Solution.startRecording(maze);
        aStar(maze.getStart(), maze.getGoal());

        if (profile) {
            return solution.endRecording(this);
        } else {
            solution.setPath(path);
            return solution;
        }
    }

    @Override
    public Path getPath() {
        return path;
    }

    private void aStar(Position start, Position goal) {
        gScore.put(start, 0);
        fScore.put(start, gScore.get(start) + start.getManhattanDistance(goal));

        open.add(start);

        while(!open.isEmpty()) {
            Position current = getNodeWithLeastFCost();

            if (current.equals(goal)) {
                reconstructPath(current);
                return;
            }

            open.remove(current);
            closed.add(current);

            for(Position adjacent : current.getNeighbors()) {

                if (closed.contains(adjacent)) continue;

                int tentativeGScore = gScore.get(current) + 10;

                if (!open.contains(adjacent) || tentativeGScore < gScore.get(adjacent)) {
                    parenthood.put(adjacent, current);
                    gScore.put(adjacent, tentativeGScore);
                    fScore.put(adjacent, gScore.get(adjacent) + adjacent.getManhattanDistance(goal));

                    if (!open.contains(adjacent)) {
                        open.add(adjacent);
                    }
                }
            }
        }
    }

    private void reconstructPath(Position current) {
        path.add(current);

        while(parenthood.get(current) != null) {
            path.add(parenthood.get(current));
            current = parenthood.get(current);
        }

        path.reverse();
    }

    @Override
    public String toString() {
        return "Shortest Path (A*)";
    }

    public Position getNodeWithLeastFCost() {
        Position selected = open.iterator().next();

        for (Position node : open) {
            if (fScore.get(node) < fScore.get(selected))
                selected = node;
        }

        return selected;
    }

}
