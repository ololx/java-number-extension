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

import java.util.ArrayList;
import java.util.List;

/**
 * @project java-number-extension
 * @created 2021-09-29 12:51
 * <p>
 * @author Alexander A. Kropotin
 */
public class BaseParsingStrategy implements ParsingStrategy<Number> {

    @Override
    public Number apply(String value) {
        if (value == null) return null;
        if (value.length() < 1) return null;

        boolean isPresent = false;
        boolean isReal = false;

        int numberSign = value.charAt(0) == '-' ? -1 : 1;
        double integer = 0d;
        double fractional = 0d;
        double fractionalPower = 10d;
        double exponent = 1d;

        int pointer = numberSign == -1 ? 1 : 0;
        while (pointer < value.length()) {
            char symbol = value.charAt(pointer);

            if (symbol == 44 || symbol == 46) {
                isReal = true;
            } else if (symbol > 47 && symbol < 58) {
                isPresent = true;

                if (isReal) {
                    fractional += ((symbol - '0') / fractionalPower);
                    fractionalPower *= 10d;
                } else {
                    integer = (integer * 10d) + (symbol - '0');
                }
            } else if ((symbol == 69 || symbol == 101)) {
                pointer++;
                if (pointer >= value.length()) continue;

                int exponentSign = 1;
                if (value.charAt(pointer) == 45) {
                    exponentSign = -1;
                    pointer++;
                } else if (value.charAt(pointer) == 43) {
                    exponentSign = 1;
                    pointer++;
                }

                double exponentPower = 0;
                while (pointer < value.length()) {
                    symbol = value.charAt(pointer);

                    if (symbol > 47 && symbol < 58) {
                        exponentPower = exponentSign * ((exponentPower * 10d) + (symbol - '0'));
                    } else if ((symbol == 69 || symbol == 101)) {
                        pointer--;
                        break;
                    }

                    pointer++;
                }

                if (exponentPower != 0) exponent *= Math.pow(10, exponentPower);
            }

            pointer++;
        }

        return !isPresent ? null : numberSign * (integer + fractional) * exponent;
    }
}
