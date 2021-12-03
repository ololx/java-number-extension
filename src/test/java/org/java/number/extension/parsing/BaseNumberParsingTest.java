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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.CSVTestCaseLoader;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The type Base number parsing test.
 *
 * @author Alexander A. Kropotin
 * @project java -number-extension
 * @created 2021 -09-29 12:59 <p>
 */
@DisplayName("The BaseNumberParsing test cases")
public class BaseNumberParsingTest {

    /**
     * Gets dirty strings for test params provider.
     *
     * @return the dirty strings for test params provider
     */
    static Stream<Arguments> getDirtyStringsForTestParamsProvider() {
        return new CSVTestCaseLoader().load("dirty-strings-cases.csv").stream()
                .map(eachCase -> Arguments.of(eachCase.toArray()));
    }

    /**
     * Apply parse dirty string with digit symbols return number.
     *
     * @param expectedValue the expected value
     * @param originValue   the origin value
     */
    @DisplayName("[positive]: test number parsing from string with dirty symbols")
    @MethodSource("getDirtyStringsForTestParamsProvider")
    @ParameterizedTest
    public void apply_parseDirtyStringWithDigitSymbols_returnNumber(Double expectedValue, String originValue) {
        Number actualValue = new BaseParsingStrategy().apply(originValue);

        assertTrue(
                actualValue.doubleValue() == expectedValue,
                String.format("Expected %s, but was %s", expectedValue, actualValue)
        );
    }
}
