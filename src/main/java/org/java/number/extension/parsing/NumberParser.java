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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @project java-number-extension
 * @created 2021-11-29 21:51
 * <p>
 * @author Alexander A. Kropotin
 */
public final class NumberParser {

    public static NumberParser newInstance() {
        return new NumberParser();
    }

    private Map<Class<? extends Number>, ParsingStrategy<? extends Number>> parsingStrategies;

    {
        parsingStrategies = Map.of(Number.class, new BaseParsingStrategy());
    }

    public NumberParser() {
        this(null);
    }

    public NumberParser(Map<Class<? extends Number>, ParsingStrategy<? extends Number>> parsingStrategies) {
        if (parsingStrategies == null) return;

        this.parsingStrategies.putAll(parsingStrategies);
    }

    public <T extends Number> T parse(T to, String from) {
        ParsingStrategy parsingStrategy = this.getParsingStrategy(to.getClass());

        T o = null;
        try {
            Class<T> class1 = (Class<T>) Class.forName(to.getClass().getCanonicalName());
            Method method = class1.getMethod("valueOf", String.class);
            o = (T) method.invoke(to, parsingStrategy.apply(from).toString());
            System.out.println(o);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return o;
    }

    private ParsingStrategy getParsingStrategy(Class<?> type) {
        ParsingStrategy parsingStrategy = this.parsingStrategies.get(type);
        if (parsingStrategy == null) {
            parsingStrategy = this.parsingStrategies.get(Number.class);
        }

        return parsingStrategy;
    }
}
