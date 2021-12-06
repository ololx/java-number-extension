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

import org.java.number.extension.parsing.internal.type.NumberTypeResolver;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * The type Number parser.
 *
 * @author Alexander A. Kropotin
 * @project java -number-extension
 * @created 2021 -11-29 21:51 <p>
 */
public final class NumberParser {

    /**
     * New instance number parser.
     *
     * @return the number parser
     */
    public static NumberParser newInstance() {
        return new NumberParser();
    }

    private Map<Class<? extends Number>, ParsingStrategy<? extends Number>> parsingStrategies;

    {
        parsingStrategies = Map.of(Number.class, new BaseParsingStrategy());
    }

    /**
     * Instantiates a new Number parser.
     */
    public NumberParser() {
        this(null);
    }

    /**
     * Instantiates a new Number parser.
     *
     * @param parsingStrategies the parsing strategies
     */
    public NumberParser(Map<Class<? extends Number>, ParsingStrategy<? extends Number>> parsingStrategies) {
        if (parsingStrategies == null) return;

        this.parsingStrategies.putAll(parsingStrategies);
    }

    /**
     * Parse t.
     *
     * @param <T>  the type parameter
     * @param type the type
     * @param from the from
     * @return the t
     */
    public <T extends Number> T parse(Class<T> type, String from) {
        NumberTypeResolver numberTypeResolver = new NumberTypeResolver();
        type = (Class<T>) numberTypeResolver.getWrapTypeForPrimitive(type);

        ParsingStrategy parsingStrategy = this.getParsingStrategy(type.getClass());

        Constructor constructor = null;
        Class<?> primitiveType = null;
        for (Constructor declaredConstructor : type.getDeclaredConstructors()) {
            if (declaredConstructor.getGenericParameterTypes().length != 1) continue;

            for (Class<?> parameterType : declaredConstructor.getParameterTypes()) {
                if (!numberTypeResolver.hasPrimitiveType((Class<? extends Number>) parameterType)) continue;
                if (primitiveType != null
                        && numberTypeResolver.compareExpressiveness(
                                (Class<? extends Number>) parameterType,
                        (Class<? extends Number>) primitiveType) > 0
                ) continue;

                primitiveType = parameterType;
                constructor = declaredConstructor;
            }
        }

        T number = null;
        try {
            Object res = parsingStrategy.apply(from);

            if (constructor == null) {
                Method method = type.getMethod("valueOf", String.class);
                number = (T) method.invoke(type, res.toString());
            }

            number = (T) constructor.newInstance(
                    res.getClass()
                            .getMethod(primitiveType.getCanonicalName() + "Value")
                            .invoke(res)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return number;
    }

    private ParsingStrategy getParsingStrategy(Class<?> type) {
        ParsingStrategy parsingStrategy = this.parsingStrategies.get(type);
        if (parsingStrategy == null) {
            parsingStrategy = this.parsingStrategies.get(Number.class);
        }

        return parsingStrategy;
    }
}
