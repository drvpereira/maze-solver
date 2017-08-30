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
 * Date: 23/05/14
 * Time: 07:32
 * To change this template use File | Settings | File Templates.
 */
public class GreedySolver implements MazeSolver {

    private List<Position> queue = new ArrayList<>();
    private Set<Position> visited = new HashSet<>();

    private Path path = new Path();

    @Override
    public Solution solve(final Maze maze) {
        Solution solution = Solution.startRecording(maze);

        Position current = maze.getStart();

        queue.add(current);

        while(!queue.isEmpty()) {

            Collections.sort(queue, new Comparator<Position>() {

                @Override
                public int compare(Position o1, Position o2) {
                    int cost1 = o1.getManhattanDistance(maze.getGoal());
                    int cost2 = o2.getManhattanDistance(maze.getGoal());
                    return cost1 - cost2;
                }
            });

            Position p = queue.remove(0);

            if (p.equals(maze.getGoal())) {
                return solution.endRecording(this);
            }

            visited.add(p);

            if (p.getEast().isAvailable() && !visited.contains(p.getEast()) && !queue.contains(p.getEast())) {
                path.add(p.getEast());
                queue.add(p.getEast());
            }

            if (p.getSouth().isAvailable() && !visited.contains(p.getSouth()) && !queue.contains(p.getSouth())) {
                path.add(p.getSouth());
                queue.add(p.getSouth());
            }

            if (p.getWest().isAvailable() && !visited.contains(p.getWest()) && !queue.contains(p.getWest())) {
                path.add(p.getWest());
                queue.add(p.getWest());
            }

            if (p.getNorth().isAvailable() && !visited.contains(p.getNorth()) && !queue.contains(p.getNorth())) {
                path.add(p.getNorth());
                queue.add(p.getNorth());
            }

        }

        return solution.endRecording(this);
    }

    @Override
    public Path getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "Greedy Search (Best-first)";
    }
}
