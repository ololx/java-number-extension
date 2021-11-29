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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @project java-number-extension
 * @created 2021-11-29 22:19
 * <p>
 * @author Alexander A. Kropotin
 */
public class NumberFactory {

    public <T extends Number> T newInstance(Class<T> type) {
        Constructor[] constructors = type.getDeclaredConstructors();

        Constructor noArgsConstructor = null;
        for (int i = 0; i < constructors.length; i++) {
            if (constructors[i].getGenericParameterTypes().length == 0) {
                noArgsConstructor = constructors[i];
                break;
            }
        }

        T c = null;

        try {
            if (noArgsConstructor != null) {
                noArgsConstructor.setAccessible(true);
                c = (T) noArgsConstructor.newInstance();
                //c = (T) c.getClass().getDeclaredMethod("valueOf").invoke(c, "123");
                noArgsConstructor.setAccessible(false);
            }

            c = (T) type.newInstance();
            //f.setAccessible(true);
        } catch (InstantiationException x) {
            x.printStackTrace();
        } catch (InvocationTargetException x) {
            x.printStackTrace();
        } catch (IllegalAccessException x) {
            x.printStackTrace();
        }

        return c;
    }
}
