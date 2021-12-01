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

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @project java-number-extension
 * @created 2021-11-30 20:51
 * <p>
 * @author Alexander A. Kropotin
 */
public class NumberTypeResolver {

    private static final Set<TypeDescription> types;

    public static final Map<String, Class<? extends Number>> primitiveTypes;

    public static final Map<String, Class<?>> wrapperTypes;

    public static final Map<Class<?>, Integer> typesNestingRules;

    static {
        primitiveTypes = Map.of(
                "byte", byte.class,
                "short", short.class,
                "int", int.class,
                "long", long.class,
                "float", float.class,
                "double", double.class
        );

        wrapperTypes = Map.of(
                "Byte", Byte.class,
                "Short", Short.class,
                "Int", Integer.class,
                "Long", Long.class,
                "Float", Float.class,
                "Double", Double.class
        );

        typesNestingRules = Map.of(
                double.class, 1,
                float.class, 2,
                long.class, 3,
                int.class, 4,
                short.class, 5,
                byte.class, 6
        );

        types = Set.of(
                new NumberTypeDescription<>(Double.class, Double.TYPE),
                new NumberTypeDescription<>(Float.class, Float.TYPE),
                new NumberTypeDescription<>(Long.class, Long.TYPE),
                new NumberTypeDescription<>(Integer.class, Integer.TYPE),
                new NumberTypeDescription<>(Short.class, Short.TYPE),
                new NumberTypeDescription<>(Byte.class, Byte.TYPE)
        );
    }

    public NumberTypeResolver() {
    }

    public Class<? extends Number> getWrapTypeForPrimitive(Class<? extends Number> primitiveType) {
        Optional<TypeDescription> typeDescription =  this.types.stream()
                .filter(type -> type.getPrimitiveType().equals(primitiveType))
                .findAny();

        return typeDescription.isPresent() ? typeDescription.get().getWrapperType() : primitiveType;
    }
}
