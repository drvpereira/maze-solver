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
import net.davidpereira.mazesolver.domain.Solution;
import net.davidpereira.mazesolver.solver.MazeSolver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Timer;

/**
 * Created with IntelliJ IDEA.
 * User: U403
 * Date: 22/05/14
 * Time: 10:02
 * To change this template use File | Settings | File Templates.
 */
public class RunSolutionAction implements ActionListener {

    private MazeController controller;

    private Timer t = new Timer();

    public RunSolutionAction(MazeController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        t.cancel();
        t = new Timer();

        controller.clearPath();

        if (!controller.isMazeDefined()) {
            JOptionPane.showMessageDialog(controller.getWindow(), "É necessário carregar um labirinto.", "Erro!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!controller.areMazePositionsDefined()) {
            JOptionPane.showMessageDialog(controller.getWindow(), "É necessário selecionar as posições inicial e final.", "Erro!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        MazeSolver solver = controller.getSelectedAlgorithm();
        Solution solution = solver.solve(controller.getMaze());

        if (!solution.hasFoundSolution()) {
            JOptionPane.showMessageDialog(controller.getWindow(), "Não foi possível encontrar uma solução nessas condições utilizando esse algoritmo.", "Erro!", JOptionPane.ERROR_MESSAGE);
        }

        controller.setSolutionValues(solution);

        final Iterator<Position> it = solution.getPath().iterator();

        t.schedule(new TimerTask() {
            public void run() {
            if (it.hasNext()) {
                controller.addPositionToPath(it.next());
                controller.repaintWindow();
            } else {
                t.cancel();
            }
            }
        }, 0, controller.getSolutionDisplaySpeed());
    }
}
