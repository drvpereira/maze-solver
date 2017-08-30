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

import net.davidpereira.mazesolver.domain.Maze;
import net.davidpereira.mazesolver.domain.Position;
import net.davidpereira.mazesolver.domain.Walker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

/**
 * Copyright 2013 David Pereira
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
public class MazeViewer extends JPanel {

    public static final int TILE_SIZE = 12;

    private Maze maze;
    private Walker walker;

    private boolean choosingPosition;
    private boolean settingInitialPosition;
    private int selectedRow;
    private int selectedCol;

    public MazeViewer() {
        setBounds(10, 10, 41 * TILE_SIZE, 41 * TILE_SIZE);
    }

    public void loadMaze(Maze maze) {
        this.maze = maze;
        this.walker = new Walker();
        this.choosingPosition = false;
        this.settingInitialPosition = false;
        this.selectedRow = 0;
        this.selectedCol = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {

        if (maze == null) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 41 * TILE_SIZE, 41 * TILE_SIZE);
            return;
        } else {

            for (int c = 0; c < maze.getWidth(); c++) {
                for (int r = 0; r < maze.getHeight(); r++) {

                    if (maze.isPositionAvailable(r, c)) {
                        g.setColor(Color.WHITE);
                    } else {
                        g.setColor(Color.BLACK);
                    }

                    g.fillRect(c * TILE_SIZE, r * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }

            if (choosingPosition) {
                if (maze.isPositionAvailable(selectedRow, selectedCol)) {
                    g.setColor(settingInitialPosition ? Color.RED : Color.GREEN);
                    g.fillRect(selectedCol * TILE_SIZE, selectedRow * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }

            if (walker.getPath() != null) {
                for (Position position : walker.getPath()) {
                    g.setColor(Color.GRAY);
                    g.fillRect(position.getColumn() * TILE_SIZE + 1, position.getRow() * TILE_SIZE + 1, TILE_SIZE - 2, TILE_SIZE - 2);
                }
            }

            if (walker.getCurrentPosition() != null) {
                g.setColor(Color.YELLOW);
                g.fillRect(walker.getCurrentPosition().getColumn() * TILE_SIZE, walker.getCurrentPosition().getRow() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }

            if (maze.getStart() != null) {
                g.setColor(Color.RED);
                g.fillRect(maze.getStart().getColumn() * TILE_SIZE, maze.getStart().getRow() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }

            if (maze.getGoal() != null) {
                g.setColor(Color.GREEN);
                g.fillRect(maze.getGoal().getColumn() * TILE_SIZE, maze.getGoal().getRow() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }

        }

    }

    public void addPositionToPath(Position position) {
        walker.walkToPosition(position);
    }

    public void addMouseSettingPositionsAction(MouseAdapter mouseAdapter) {
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    public void enablePositionChoosing(boolean settingInitialPosition) {
        this.choosingPosition = true;
        this.settingInitialPosition = settingInitialPosition;
    }

    public void setMousePositionInMaze(int row, int col) {
        this.selectedRow = row;
        this.selectedCol = col;
    }

    public boolean isChoosingPosition() {
        return choosingPosition;
    }

    public void disablePositionChoosing() {
        this.choosingPosition = false;
    }

    public boolean isDefiningInitialPosition() {
        return settingInitialPosition;
    }

    public Position getCurrentMousePosition() {
        return maze.getPosition(selectedRow, selectedCol);
    }

    public void clearPath() {
        walker.clearPath();
    }
}
