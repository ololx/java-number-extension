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
package org.java.number.extension.parsing;

import java.util.Map;

/**
 * @project java-number-extension
 * @created 2021-11-30 20:51
 * <p>
 * @author Alexander A. Kropotin
 */
public class NumberTypeResolver {

    public static final Map<String, Class<?>> primitiveTypes;

    public static final Map<String, Class<?>> wrapperTypes;

    public static final Map<Class<?>, Integer> typesNestingRules;

    //private static final Map<Class<?>, Class<?>> primitiveToWrapperLinks;

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
    }
}
