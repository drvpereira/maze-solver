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
package net.davidpereira.mazesolver.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: U403
 * Date: 21/05/14
 * Time: 08:46
 * To change this template use File | Settings | File Templates.
 */
public class Path implements Iterable<Position>, Serializable {

    private List<Position> positions = new ArrayList<>();

    @Override
    public Iterator<Position> iterator() {
        return positions.iterator();
    }

    public void add(Position current) {
        positions.add(current);
    }

    public int size() {
        return positions.size();
    }

    public void reverse() {
        Collections.reverse(positions);
    }

    public boolean contains(Position position) {
        return positions.contains(position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Path positions1 = (Path) o;

        if (positions != null ? !positions.equals(positions1.positions) : positions1.positions != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return positions != null ? positions.hashCode() : 0;
    }
}
