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

import java.lang.reflect.Type;

/**
 * The interface Number type.
 *
 * @param <W> the type parameter
 * @param <P> the type parameter
 * @author Alexander A. Kropotin
 * @project java -number-extension
 * @created 2021 -12-01 20:32 <p>
 */
public interface NumberType<W extends Number, P extends Number> extends Type {

    /**
     * Gets wrapper type.
     *
     * @return the wrapper type
     */
    Class<W> getWrapperType();

    /**
     * Gets primitive type.
     *
     * @return the primitive type
     */
    Class<P> getPrimitiveType();

    /**
     * Has primitive type boolean.
     *
     * @return the boolean
     */
    boolean hasPrimitiveType();

    @Override
    default String getTypeName() {
        String typeName = this.getWrapperType().getCanonicalName();

        int lastDotIndex = 0;
        if ((lastDotIndex = typeName.lastIndexOf("\\.")) > -1) {
            typeName = typeName.substring(lastDotIndex);
        }

        return typeName;
    }
}
