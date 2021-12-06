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

import java.util.Collection;
import java.util.List;

/**
 * The interface Graph.
 *
 * @param <V> the type parameter
 * @author Alexander A. Kropotin
 * @project java -number-extension
 * @created 2021 -12-04 15:06 <p>
 */
public interface Graph<V> {

    /**
     * Add vertex.
     *
     * @param vertex the vertex
     */
    void addVertex(V vertex);

    /**
     * Remove vertex v.
     *
     * @param vertex the vertex
     * @return the v
     */
    V removeVertex(V vertex);

    Collection<V> getVertexes();

    /**
     * Add edge.
     *
     * @param vertex the vertex
     * @param apex   the apex
     */
    void addEdge(V vertex, V apex);

    /**
     * Remove edge boolean.
     *
     * @param vertex the vertex
     * @param apex   the apex
     * @return the boolean
     */
    boolean removeEdge(V vertex, V apex);

    /**
     * Gets vertex count.
     *
     * @return the vertex count
     */
    long getVertexCount();

    /**
     * Gets edges count.
     *
     * @return the edges count
     */
    long getEdgesCount();

    /**
     * Has vertex boolean.
     *
     * @param vertex the vertex
     * @return the boolean
     */
    boolean hasVertex(V vertex);

    /**
     * Has edge boolean.
     *
     * @param vertex the vertex
     * @param apex   the apex
     * @return the boolean
     */
    boolean hasEdge(V vertex, V apex);
}