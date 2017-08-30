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

import com.javamex.classmexer.MemoryUtil;
import net.davidpereira.mazesolver.solver.MazeSolver;
import net.davidpereira.mazesolver.solver.ShortestPathSolver;

/**
 * Created with IntelliJ IDEA.
 * User: U403
 * Date: 21/05/14
 * Time: 15:35
 * To change this template use File | Settings | File Templates.
 */
public class Solution {

    private Path path;

    private long time;
    private long memory;
    private int nodes;
    private boolean shortestPath;

    private boolean foundSolution;
    private long init;
    private Maze maze;

    private Solution() {}

    public static Solution startRecording(Maze maze) {
        Solution s = new Solution();
        s.init = System.currentTimeMillis();
        s.maze = maze;
        return s;
    }

    public Path getPath() {
        return path;
    }

    public long getTime() {
        return time;
    }

    public long getMemory() {
        return memory;
    }

    public int getNodes() {
        return nodes;
    }

    public boolean isShortestPath() {
        return shortestPath;
    }

    public boolean hasFoundSolution() {
        return foundSolution;
    }

    public Solution endRecording(MazeSolver solver) {
        Solution optimal = new ShortestPathSolver(false).solve(maze);

        this.path = solver.getPath();
        this.time = System.currentTimeMillis() - init;
        this.memory = MemoryUtil.deepMemoryUsageOf(solver) / 1024;
        this.nodes = path.size();
        this.shortestPath = path.equals(optimal.getPath());
        this.foundSolution = true;

        return this;
    }

    public void setPath(Path path) {
        this.path = path;
    }
}
