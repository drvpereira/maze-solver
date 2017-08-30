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
package net.davidpereira.mazesolver.controller;

import net.davidpereira.mazesolver.domain.Maze;
import net.davidpereira.mazesolver.domain.Position;
import net.davidpereira.mazesolver.domain.Solution;
import net.davidpereira.mazesolver.domain.Walker;
import net.davidpereira.mazesolver.solver.MazeSolver;
import net.davidpereira.mazesolver.view.MazeWindow;

/**
 * Created with IntelliJ IDEA.
 * User: U403
 * Date: 22/05/14
 * Time: 07:37
 * To change this template use File | Settings | File Templates.
 */
public class MazeController {

    private MazeWindow window;

    private Maze maze;

    public MazeController(MazeWindow window) {
        this.window = window;

        window.addRunSolutionAction(new RunSolutionAction(this));
        window.addChooseMazeFileAction(new ChooseMazeFileAction(this));
        window.addSetInitialPositionAction(new EnablePositionSettingAction(this, true));
        window.addSetFinalPositionAction(new EnablePositionSettingAction(this, false));
        window.addMouseSettingPositionsAction(new MouseSettingPositionsAction(this));
    }

    public MazeWindow getWindow() {
        return window;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
        this.maze.setStart(null);
        this.maze.setGoal(null);

        window.loadMaze(maze);
    }

    public void repaintWindow() {
        window.repaint();
    }

    public boolean isMazeDefined() {
        return maze != null;
    }

    public boolean areMazePositionsDefined() {
        return maze.areControlPositionsDefined();
    }

    public MazeSolver getSelectedAlgorithm() {
        return window.getSelectedAlgorithm();
    }

    public Maze getMaze() {
        return maze;
    }

    public void setSolutionValues(Solution solution) {
        window.setSolutionValues(solution);
    }

    public long getSolutionDisplaySpeed() {
        return window.getSolutionDisplaySpeed();
    }

    public void clearPath() {
        window.clearPath();
    }

    public void addPositionToPath(Position position) {
        window.addPositionToPath(position);
    }

    public void enablePositionChoosing(boolean settingInitialPosition) {
        window.enablePositionChoosing(settingInitialPosition);
    }

    public void setMousePositionInMaze(int row, int col) {
        window.setMousePositionInMaze(row, col);
    }

    public boolean isChoosingPosition() {
        return window.isChoosingPosition();
    }

    public Position getCurrentMousePosition() {
        return window.getCurrentMousePosition();
    }

    public void disablePositionChoosing() {
        window.disablePositionChoosing();
    }

    public boolean isDefiningInitialPosition() {
        return window.isDefiningInitialPosition();
    }
}
