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

import net.davidpereira.mazesolver.exceptions.ImageLoadingException;
import net.davidpereira.mazesolver.loader.ImageMazeLoader;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: U403
 * Date: 22/05/14
 * Time: 10:01
 * To change this template use File | Settings | File Templates.
 */
public class ChooseMazeFileAction implements ListSelectionListener {

    private MazeController controller;
    private JList<File> fileList;

    public ChooseMazeFileAction(MazeController controller) {
        this.controller = controller;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        controller.setMaze(new ImageMazeLoader(fileList.getSelectedValue().getAbsolutePath()).loadMaze());
        try {
            controller.repaintWindow();
        } catch(ImageLoadingException ex) {
            JOptionPane.showMessageDialog(controller.getWindow(), "Imagem não suportada.", "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setFileList(JList<File> fileList) {
        this.fileList = fileList;
    }

}
