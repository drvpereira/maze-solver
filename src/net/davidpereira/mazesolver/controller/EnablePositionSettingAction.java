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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: U403
 * Date: 22/05/14
 * Time: 10:01
 * To change this template use File | Settings | File Templates.
 */
public class EnablePositionSettingAction implements ActionListener {

    private MazeController controller;
    private boolean settingInitialPosition;

    public EnablePositionSettingAction(MazeController controller, boolean settingInitialPosition) {
        this.controller = controller;
        this.settingInitialPosition = settingInitialPosition;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.enablePositionChoosing(settingInitialPosition);
    }
}
