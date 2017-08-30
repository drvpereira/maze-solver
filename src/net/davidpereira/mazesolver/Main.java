/*
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
package net.davidpereira.mazesolver;

import net.davidpereira.mazesolver.controller.MazeController;
import net.davidpereira.mazesolver.view.MazeWindow;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: drvpereira
 * Date: 30/04/14
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MazeController(new MazeWindow());
            }
        });
    }

}