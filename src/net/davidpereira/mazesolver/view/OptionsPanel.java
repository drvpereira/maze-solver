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
import net.davidpereira.mazesolver.domain.Solution;
import net.davidpereira.mazesolver.exceptions.AlgorithmLoadingException;
import net.davidpereira.mazesolver.solver.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: U403
 * Date: 21/05/14
 * Time: 10:25
 * To change this template use File | Settings | File Templates.
 */
public class OptionsPanel extends JPanel {

    public OptionsPanel() {
        setBounds(515, 10, 375, 495);
        setBorder(BorderFactory.createTitledBorder("Opções"));
        setLayout(null);

        configMazeLoadingComponents();
        configPositionSettingComponents();
        configAlgorithmChoosingComponents();
        configSolutionDisplayingComponents();
    }

    private JLabel chooseMazeFileLabel;
    private JList<File> chooseMazeFileList;

    private void configMazeLoadingComponents() {
        chooseMazeFileLabel = new JLabel("1. Selecione uma imagem para carregar o labirinto:");
        chooseMazeFileLabel.setBounds(15, 40, 350, 20);
        add(chooseMazeFileLabel);

        DefaultListModel<File> model = new DefaultListModel<>();
        File[] files = new File("./imagens").listFiles();
        if (files != null) {
            for (File file : files) {
                model.addElement(file);
            }
        }

        chooseMazeFileList = new JList<>();
        chooseMazeFileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        chooseMazeFileList.setModel(model);
        JScrollPane scroll = new JScrollPane(chooseMazeFileList);
        scroll.setBounds(70, 70, 230, 70);
        add(scroll);
    }

    private JLabel setPositionsLabel;
    private JButton setInitialPositionButton;
    private JButton setFinalPositionButton;

    private void configPositionSettingComponents() {
        setPositionsLabel = new JLabel("2. Selecione as posições inicial e final:");
        setPositionsLabel.setBounds(15, 150, 300, 20);
        add(setPositionsLabel);

        setInitialPositionButton = new JButton("Posição Inicial");
        setInitialPositionButton.setBounds(60, 180, 120, 25);
        add(setInitialPositionButton);

        setFinalPositionButton = new JButton("Posição Final");
        setFinalPositionButton.setBounds(185, 180, 120, 25);
        add(setFinalPositionButton);
    }

    private JLabel chooseAlgorithmLabel;
    private JComboBox<MazeSolver> chooseAlgorithmCombo;
    private JSlider solutionDisplaySpeedSlider;
    private JButton runMazeSolverButton;
    private JLabel solutionDisplaySlowLabel;
    private JLabel solutionDisplayFastLabel;

    private void configAlgorithmChoosingComponents() {
        chooseAlgorithmLabel = new JLabel("3. Selecione o algoritmo para resolver o labirinto:");
        chooseAlgorithmLabel.setBounds(15, 220, 350, 20);
        add(chooseAlgorithmLabel);

        DefaultComboBoxModel<MazeSolver> model = new DefaultComboBoxModel<>();
        model.addElement(new BacktrackingSolver());
        model.addElement(new GreedySolver());
        model.addElement(new ShortestPathSolver());
        model.addElement(new WallFollowerSolver());

        chooseAlgorithmCombo = new JComboBox<>(model);
        chooseAlgorithmCombo.setEditable(false);
        chooseAlgorithmCombo.setBounds(100, 250, 180, 25);
        add(chooseAlgorithmCombo);

        solutionDisplaySlowLabel = new JLabel("Lento");
        solutionDisplaySlowLabel.setBounds(285, 285, 60, 20);
        add(solutionDisplaySlowLabel);

        solutionDisplayFastLabel = new JLabel("Rápido");
        solutionDisplayFastLabel.setBounds(60, 285, 60, 20);
        add(solutionDisplayFastLabel);

        solutionDisplaySpeedSlider = new JSlider();
        solutionDisplaySpeedSlider.setBounds(100, 285, 185, 25);
        solutionDisplaySpeedSlider.setMajorTickSpacing(5);
        solutionDisplaySpeedSlider.setMinorTickSpacing(1);
        solutionDisplaySpeedSlider.setPaintTicks(true);
        solutionDisplaySpeedSlider.setMinimum(1);
        solutionDisplaySpeedSlider.setMaximum(15);
        solutionDisplaySpeedSlider.setValue(2);
        add(solutionDisplaySpeedSlider);

        runMazeSolverButton = new JButton("Resolver");
        runMazeSolverButton.setBounds(130, 325, 120, 25);
        add(runMazeSolverButton);
    }


    private JLabel showResultsLabel;
    private JLabel foundLabel;
    private JLabel foundValueLabel;
    private JLabel timeLabel;
    private JLabel timeValueLabel;
    private JLabel memUsageLabel;
    private JLabel memUsageValueLabel;
    private JLabel numNodesLabel;
    private JLabel numNodesValueLabel;
    private JLabel shortestPathLabel;
    private JLabel shortestPathValueLabel;

    private void configSolutionDisplayingComponents() {
        showResultsLabel = new JLabel("4. Resultados:");
        showResultsLabel.setBounds(15, 360, 300, 20);
        add(showResultsLabel);

        foundLabel = new JLabel("Encontrou solução?");
        foundLabel.setBounds(120, 385, 150, 20);
        add(foundLabel);

        foundValueLabel = new JLabel("Não");
        foundValueLabel.setBounds(255, 385, 50, 20);
        add(foundValueLabel);

        timeLabel = new JLabel("Tempo: ");
        timeLabel.setBounds(35, 410, 150, 20);
        add(timeLabel);

        timeValueLabel = new JLabel("0 ms");
        timeValueLabel.setBounds(150, 410, 50, 20);
        add(timeValueLabel);

        memUsageLabel = new JLabel("Memória: ");
        memUsageLabel.setBounds(200, 410, 150, 20);
        add(memUsageLabel);

        memUsageValueLabel = new JLabel("0 kb");
        memUsageValueLabel.setBounds(315, 410, 50, 20);
        add(memUsageValueLabel);

        numNodesLabel = new JLabel("Número de Nós: ");
        numNodesLabel.setBounds(35, 435, 150, 20);
        add(numNodesLabel);

        numNodesValueLabel = new JLabel("0 nós");
        numNodesValueLabel.setBounds(150, 435, 50, 20);
        add(numNodesValueLabel);

        shortestPathLabel = new JLabel("Menor caminho? ");
        shortestPathLabel.setBounds(200, 435, 150, 20);
        add(shortestPathLabel);

        shortestPathValueLabel = new JLabel("Não");
        shortestPathValueLabel.setBounds(315, 435, 50, 20);
        add(shortestPathValueLabel);
    }

    public void addRunSoluctionAction(ActionListener actionListener) {
        runMazeSolverButton.addActionListener(actionListener);
    }

    public void addChooseMazeFileAction(ChooseMazeFileAction listSelecionListener) {
        listSelecionListener.setFileList(chooseMazeFileList);
        chooseMazeFileList.addListSelectionListener(listSelecionListener);
    }

    public void addSetInitialPositionAction(ActionListener actionListener) {
        setInitialPositionButton.addActionListener(actionListener);
    }

    public void addSetFinalPositionAction(ActionListener actionListener) {
        setFinalPositionButton.addActionListener(actionListener);
    }

    public MazeSolver getSelectedAlgorithm() {
        try {
            return (MazeSolver) chooseAlgorithmCombo.getSelectedItem().getClass().newInstance();
        } catch (Exception e) {
            throw new AlgorithmLoadingException(e.getMessage(), e);
        }
    }

    public void setSolutionValues(Solution solution) {
        foundValueLabel.setText(solution.hasFoundSolution() ? "Sim" : "Não");
        timeValueLabel.setText(solution.getTime() + " ms");
        memUsageValueLabel.setText(solution.getMemory() + " kb");
        numNodesValueLabel.setText(solution.getNodes() + " ");
        shortestPathValueLabel.setText(solution.isShortestPath() ? "Sim" : "Não");
    }

    public long getSolutionDisplaySpeed() {
        return solutionDisplaySpeedSlider.getValue() * 10;
    }

}
