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

/**
 * The type Basic number type.
 *
 * @param <W> the type parameter
 * @param <P> the type parameter
 * @author Alexander A. Kropotin
 * @project java -number-extension
 * @created 2021 -12-01 20:52 <p>
 */
public class BasicNumberType<W extends Number, P extends Number> implements NumberType<W, P> {

    private final Class<W> wrapperType;

    private final Class<P> primitiveType;

    /**
     * Instantiates a new Basic number type.
     *
     * @param wrapperType the wrapper type
     */
    public BasicNumberType(Class<W> wrapperType) {
        this(wrapperType, null);
    }

    /**
     * Instantiates a new Basic number type.
     *
     * @param wrapperType   the wrapper type
     * @param primitiveType the primitive type
     */
    public BasicNumberType(Class<W> wrapperType, Class<P> primitiveType) {
        if (wrapperType == null)
            throw new NullPointerException("The wrapper type couldn't be null");
        if (wrapperType.isPrimitive())
            throw new IllegalArgumentException("The wrapper type couldn't be primitive");
        this.wrapperType = wrapperType;

        this.primitiveType = primitiveType;

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof BasicNumberType))
            return false;

        BasicNumberType<?, ?> other = (BasicNumberType<?, ?>) obj;

        return this.wrapperType == other.wrapperType
                && this.primitiveType == other.primitiveType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;

        int result = 1;
        result = prime * result + wrapperType.hashCode();
        result = prime * result + ((primitiveType == null) ? 0 : primitiveType.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return this.getClass().getCanonicalName() + "{\n" +
                "wrapperType=" + wrapperType + "\n" +
                ", primitiveType=" + primitiveType + "\n" +
                '}';
    }

    @Override
    public Class<W> getWrapperType() {
        return wrapperType;
    }

    @Override
    public Class<P> getPrimitiveType() {
        return primitiveType;
    }

    @Override
    public boolean hasPrimitiveType() {
        return this.primitiveType != null;
    }
}
