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
package net.davidpereira.mazesolver.view;

import net.davidpereira.mazesolver.controller.ChooseMazeFileAction;
import net.davidpereira.mazesolver.controller.MazeController;
import net.davidpereira.mazesolver.domain.Maze;
import net.davidpereira.mazesolver.domain.Position;
import net.davidpereira.mazesolver.domain.Solution;
import net.davidpereira.mazesolver.solver.MazeSolver;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: U403
 * Date: 21/05/14
 * Time: 10:07
 * To change this template use File | Settings | File Templates.
 */
public class MazeWindow extends JFrame {

    private MazeViewer mazePanel;
    private OptionsPanel optionsPanel;

    public MazeWindow() {
        mazePanel = new MazeViewer();
        optionsPanel = new OptionsPanel();

        setTitle("Algoritmos de Solução de Labirintos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 550);
        setLayout(null);
        setResizable(false);

        add(mazePanel);
        add(optionsPanel);

        setVisible(true);
    }

    public void addRunSolutionAction(ActionListener actionListener) {
        optionsPanel.addRunSoluctionAction(actionListener);
    }

    public void addChooseMazeFileAction(ChooseMazeFileAction listSelecionListener) {
        optionsPanel.addChooseMazeFileAction(listSelecionListener);
    }

    public void addSetInitialPositionAction(ActionListener actionListener) {
        optionsPanel.addSetInitialPositionAction(actionListener);
    }
    public void addSetFinalPositionAction(ActionListener actionListener) {
        optionsPanel.addSetFinalPositionAction(actionListener);
    }

    public MazeSolver getSelectedAlgorithm() {
        return optionsPanel.getSelectedAlgorithm();
    }

    public void setSolutionValues(Solution solution) {
        optionsPanel.setSolutionValues(solution);
    }

    public long getSolutionDisplaySpeed() {
        return optionsPanel.getSolutionDisplaySpeed();
    }

    public void addMouseSettingPositionsAction(MouseAdapter mouseAdapter) {
        mazePanel.addMouseSettingPositionsAction(mouseAdapter);
    }

    public void addPositionToPath(Position position) {
        mazePanel.addPositionToPath(position);
    }

    public void loadMaze(Maze maze) {
        mazePanel.loadMaze(maze);
    }

    public void enablePositionChoosing(boolean settingInitialPosition) {
        mazePanel.enablePositionChoosing(settingInitialPosition);
    }

    public void setMousePositionInMaze(int row, int col) {
        mazePanel.setMousePositionInMaze(row, col);
    }

    public boolean isChoosingPosition() {
        return mazePanel.isChoosingPosition();
    }

    public void disablePositionChoosing() {
        mazePanel.disablePositionChoosing();
    }

    public boolean isDefiningInitialPosition() {
        return mazePanel.isDefiningInitialPosition();
    }

    public Position getCurrentMousePosition() {
        return mazePanel.getCurrentMousePosition();
    }

    public void clearPath() {
        mazePanel.clearPath();
    }
}
