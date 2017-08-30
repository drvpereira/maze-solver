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

import net.davidpereira.mazesolver.domain.Position;
import net.davidpereira.mazesolver.view.MazeViewer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created with IntelliJ IDEA.
 * User: U403
 * Date: 22/05/14
 * Time: 10:02
 * To change this template use File | Settings | File Templates.
 */
public class MouseSettingPositionsAction extends MouseAdapter {

    private MazeController controller;

    public MouseSettingPositionsAction(MazeController controller) {
        this.controller = controller;
    }

    public void mouseMoved(MouseEvent e) {
        int row = e.getY() / MazeViewer.TILE_SIZE;
        int col = e.getX() / MazeViewer.TILE_SIZE;

        controller.setMousePositionInMaze(row, col);
        controller.repaintWindow();
    }

    public void mouseClicked(MouseEvent e) {
        if (controller.isChoosingPosition()) {

            Position position = controller.getCurrentMousePosition();

            if (position.isAvailable()) {
                if (controller.isDefiningInitialPosition()) {
                    controller.getMaze().setStart(position);
                } else {
                    controller.getMaze().setGoal(position);
                }

                controller.disablePositionChoosing();
                controller.repaintWindow();
            }
        }
    }
}
