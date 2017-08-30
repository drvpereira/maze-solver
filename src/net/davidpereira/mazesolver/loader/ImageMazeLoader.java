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
package net.davidpereira.mazesolver.loader;

import net.davidpereira.mazesolver.domain.Maze;
import net.davidpereira.mazesolver.domain.Position;
import net.davidpereira.mazesolver.util.Matrix;
import net.davidpereira.mazesolver.exceptions.ImageLoadingException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: drvpereira
 * Date: 30/04/14
 */
public class ImageMazeLoader implements MazeLoader {

    private String source;

    public ImageMazeLoader(String source) {
        this.source = source;
    }

    @Override
    public Maze loadMaze() {
        try {
            File mazeImageFile = new File(source);

            BufferedImage image = ImageIO.read(mazeImageFile);
            Matrix buffer = normalizeCols(normalizeRows(readPixels(image)));

            return new Maze(toPositionMatrix(buffer));
        } catch (Exception e) {
            throw new ImageLoadingException("Could not read maze image file.", e);
        }
    }

    private Position[][] toPositionMatrix(Matrix buffer) {
        Position[][] positions = new Position[buffer.getRowCount()][buffer.getColCount()];

        for (int i = 0; i < buffer.getRowCount(); i++) {
            for (int j = 0; j < buffer.getColCount(); j++) {
                positions[i][j] = getPosition(positions, buffer, i, j);
                positions[i][j].setNorth(getPosition(positions, buffer, i-1, j));
                positions[i][j].setSouth(getPosition(positions, buffer, i+1, j));
                positions[i][j].setEast(getPosition(positions, buffer, i, j+1));
                positions[i][j].setWest(getPosition(positions, buffer, i, j-1));
            }
        }

        return positions;
    }

    private Position getPosition(Position[][] positions, Matrix buffer, int i, int j) {

        if (i > 0 && j > 0 && i < positions.length && j < positions[0].length) {
            if (positions[i][j] != null) {
                return positions[i][j];
            } else {
                return positions[i][j] = new Position(i, j, buffer.get(i, j) == ' ');
            }
        } else {
            return new Position(i, j, false);
        }
    }

    private Matrix readPixels(BufferedImage image) {
        Matrix buffer = new Matrix(image.getWidth(), image.getHeight());

        for (int c = 0; c < image.getWidth(); c++) {
            for (int r = 0; r < image.getHeight(); r++) {
                Color color = new Color(image.getRGB(c, r));
                buffer.set(r, c, Color.BLACK.equals(color) ? '.' : ' ');
            }
        }

        return buffer;
    }

    private Matrix normalizeRows(Matrix buffer) {
        int lastRowNum = 0;
        int currentRowNum = 1;

        while(currentRowNum < buffer.getRowCount()) {
            if (buffer.getRow(lastRowNum).equals(buffer.getRow(currentRowNum))) {
                buffer = buffer.deleteRow(currentRowNum);
            } else {
                lastRowNum++;
                currentRowNum = lastRowNum + 1;
            }
        }

        return buffer;
    }

    private Matrix normalizeCols(Matrix buffer) {
        int lastColNum = 0;
        int currentColNum = 1;

        while(currentColNum < buffer.getColCount()) {
            if (buffer.getCol(lastColNum).equals(buffer.getCol(currentColNum))) {
                buffer = buffer.deleteCol(currentColNum);
            } else {
                lastColNum++;
                currentColNum = lastColNum + 1;
            }
        }

        return buffer;
    }


}
