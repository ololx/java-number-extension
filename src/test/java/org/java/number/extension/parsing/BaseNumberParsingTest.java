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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * @project java-number-extension
 * @created 2021-09-29 12:59
 * <p>
 * @author Alexander A. Kropotin
 */
@DisplayName("The BaseNumberParsing test cases")
public class BaseNumberParsingTest {

    static Stream<Arguments> getDirtyStringsForTestParamsProvider() {
        return Stream.of(
                arguments(1.0, "01"),
                arguments(1.0, "01.0"),
                arguments(1.0, "1.0"),
                arguments(1.0, "1.0E0"),
                arguments(1.0, "1FckUp.0"),
                arguments(10000.0, "1E+2E+2"),
                arguments(100.0, "1E2E-2E+2")
        );
    }

    @DisplayName("[positive]: test number parsing from string with dirty symbols")
    @MethodSource("getDirtyStringsForTestParamsProvider")
    @ParameterizedTest
    public void parse_parseDirtyStringWithDigitSymbols_returnNumber(Number expectedValue, String originValue) {
        Number actualValue = new BaseParsingStrategy().apply(originValue);

        assertTrue(
                actualValue.equals(expectedValue),
                String.format("Expected %s, but was %s", expectedValue, actualValue)
        );
    }

    @MethodSource("getDirtyStringsForTestParamsProvider")
    @ParameterizedTest
    public void parse_parseDirtyStringWithDigitSymbols_returnConcreteNumber(Double expectedValue, String originValue) {
        Double actualValue = NumberParser.newInstance().parse(new Double(0), originValue);
        System.out.println(actualValue);

        assertTrue(
                actualValue.equals(expectedValue),
                String.format("Expected %s, but was %s", expectedValue, actualValue)
        );
    }
}
