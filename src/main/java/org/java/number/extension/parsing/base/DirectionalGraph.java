/**
 * Copyright 2021 the project java-number-extension authors
 * and the original author or authors annotated by {@author}
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.java.number.extension.parsing.base;

import java.util.*;

/**
 * The type Directional graph.
 *
 * @param <V> the type parameter
 * @author Alexander A. Kropotin
 * @project java -number-extension
 * @created 2021 -12-06 09:52 <p>
 */
public class DirectionalGraph<V> implements Graph<V> {

    private Map<V, List<V>> vertices = new HashMap<>();

    @Override
    public void addVertex(V vertex) {
        this.vertices.putIfAbsent(vertex, new ArrayList<>());
    }

    @Override
    public V removeVertex(V vertex) {
        this.vertices.values().stream().forEach(e -> e.remove(vertex));
        this.vertices.remove(vertex);

        return vertex;
    }

    @Override
    public Set<V> getVertexes() {
        return this.vertices.keySet();
    }

    @Override
    public void addEdge(V vertex, V apex) {
        this.addVertex(vertex);
        this.addVertex(apex);
        this.vertices.get(vertex).add(apex);
    }

    @Override
    public boolean removeEdge(V vertex, V apex) {
        List<V> edges = this.vertices.get(vertex);
        if (edges != null && edges.contains(apex)) {
            edges.remove(apex);

            return true;
        }

        return false;
    }

    @Override
    public long getVertexCount() {
        return this.vertices.size();
    }

    @Override
    public long getEdgesCount() {
        return this.vertices.entrySet().stream()
                .map(eachVertex -> Long.valueOf(eachVertex.getValue().size()))
                .reduce(Long::sum)
                .orElse(0L);
    }

    @Override
    public boolean hasVertex(V vertex) {
        return this.vertices.containsKey(vertex);
    }

    @Override
    public boolean hasEdge(V vertex, V apex) {
        List<V> edges = this.vertices.get(vertex);
        if (edges == null)
            return false;

        if (edges.contains(apex))
            return true;

        for (V newVertex: edges) {
            if (hasEdge(newVertex, apex)) {
                return true;
            }
        }

        return false;
    }
}
