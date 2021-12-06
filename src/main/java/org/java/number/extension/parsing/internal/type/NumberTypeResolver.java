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
package org.java.number.extension.parsing.internal.type;

import org.java.number.extension.parsing.base.DirectionalGraph;
import org.java.number.extension.parsing.base.Graph;

import java.util.Optional;
import java.util.Set;

/**
 * The type Number type resolver.
 *
 * @author Alexander A. Kropotin
 * @project java -number-extension
 * @created 2021 -11-30 20:51 <p>
 */
public class NumberTypeResolver {

    private static Set<NumberType> types;

    public static Graph<NumberType> typesExpressivity;

    static {
        typesExpressivity = new DirectionalGraph<>();
        typesExpressivity.addEdge(
                BasicNumberType.newInstance(Byte.class, Byte.TYPE),
                BasicNumberType.newInstance(Short.class, Short.TYPE)
        );
        typesExpressivity.addEdge(
                BasicNumberType.newInstance(Short.class, Short.TYPE),
                BasicNumberType.newInstance(Integer.class, Integer.TYPE)
        );
        typesExpressivity.addEdge(
                BasicNumberType.newInstance(Integer.class, Integer.TYPE),
                BasicNumberType.newInstance(Long.class, Long.TYPE)
        );
        typesExpressivity.addEdge(
                BasicNumberType.newInstance(Long.class, Long.TYPE),
                BasicNumberType.newInstance(Float.class, Float.TYPE)
        );
        typesExpressivity.addEdge(
                BasicNumberType.newInstance(Float.class, Float.TYPE),
                BasicNumberType.newInstance(Double.class, Double.TYPE)
        );

        types = (Set<NumberType>) typesExpressivity.getVertexes();
    }

    /**
     * Instantiates a new Number type resolver.
     */
    public NumberTypeResolver() {
    }

    /**
     * Gets wrap type for primitive.
     *
     * @param primitiveType the primitive type
     * @return the wrap type for primitive
     */
    public Class<? extends Number> getWrapTypeForPrimitive(Class<? extends Number> primitiveType) {
        Optional<NumberType> typeDescription =  this.types.stream()
                .filter(type -> type.getPrimitiveType().equals(primitiveType))
                .findAny();

        return typeDescription.isPresent() ? typeDescription.get().getWrapperType() : primitiveType;
    }

    /**
     * Gets for.
     *
     * @param clazz the clazz
     * @return the for
     */
    public NumberType getFor(Class<? extends Number> clazz) {
        Optional<NumberType> typeDescription =  this.types.stream()
                .filter(type -> type.getPrimitiveType().equals(clazz) || type.getWrapperType().equals(clazz))
                .findAny();

        return typeDescription.isPresent() ? typeDescription.get() : new BasicNumberType(clazz);
    }

    /**
     * Has primitive type boolean.
     *
     * @param primitiveType the primitive type
     * @return the boolean
     */
    public boolean hasPrimitiveType(Class<? extends Number> primitiveType) {
        return this.getFor(primitiveType).hasPrimitiveType();
    }

    public int compareExpressiveness(Class<? extends Number> type, Class<? extends Number> otherType) {
        return compareExpressiveness(this.getFor(type), this.getFor(otherType));
    }

    public int compareExpressiveness(NumberType type, NumberType otherType) {
        if (typesExpressivity.hasEdge(otherType, type))
            return 1;
        else if (typesExpressivity.hasEdge(type, otherType))
            return -1;
        else
            return 0;
    }
}
