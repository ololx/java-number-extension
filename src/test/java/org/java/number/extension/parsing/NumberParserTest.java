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

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The type Number parser test.
 *
 * @author Alexander A. Kropotin
 * @project java -number-extension
 * @created 2021 -11-30 21:41 <p>
 */
@DisplayName("The BaseNumberParsing test cases")
public class NumberParserTest {

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
     * Parse parse dirty string with digit symbols return double number.
     *
     * @param expectedValue the expected value
     * @param originValue   the origin value
     */
    @MethodSource("getDirtyStringsForTestParamsProvider")
    @ParameterizedTest
    public void parse_parseDirtyStringWithDigitSymbols_returnDoubleNumber(Double expectedValue, String originValue) {
        Double actualValue = NumberParser.newInstance()
                .parse(Double.class, originValue);

        assertTrue(
                actualValue.equals(expectedValue),
                String.format("Expected %s, but was %s", expectedValue, actualValue)
        );
    }

    /**
     * Parse parse dirty string with digit symbols return integer number.
     *
     * @param expectedValue the expected value
     * @param originValue   the origin value
     */
    @MethodSource("getDirtyStringsForTestParamsProvider")
    @ParameterizedTest
    public void parse_parseDirtyStringWithDigitSymbols_returnIntegerNumber(Double expectedValue, String originValue) {
        Integer actualValue = NumberParser.newInstance().parse(Integer.class, originValue);

        assertTrue(
                actualValue.equals(expectedValue.intValue()),
                String.format("Expected %s, but was %s", expectedValue, actualValue)
        );
    }

    /**
     * Parse parse dirty string with digit symbols return float number.
     *
     * @param expectedValue the expected value
     * @param originValue   the origin value
     */
    @MethodSource("getDirtyStringsForTestParamsProvider")
    @ParameterizedTest
    public void parse_parseDirtyStringWithDigitSymbols_returnFloatNumber(Double expectedValue, String originValue) {
        Float actualValue = NumberParser.newInstance().parse(Float.class, originValue);

        assertTrue(
                actualValue.equals(expectedValue.floatValue()),
                String.format("Expected %s, but was %s", expectedValue, actualValue)
        );
    }

    /**
     * Parse parse dirty string with digit symbols return primitive double number.
     *
     * @param expectedValue the expected value
     * @param originValue   the origin value
     */
    @MethodSource("getDirtyStringsForTestParamsProvider")
    @ParameterizedTest
    public void parse_parseDirtyStringWithDigitSymbols_returnPrimitiveDoubleNumber(Double expectedValue, String originValue) {
        double actualValue = NumberParser.newInstance().parse(double.class, originValue);

        assertTrue(
                actualValue == expectedValue,
                String.format("Expected %s, but was %s", expectedValue, actualValue)
        );
    }

    /**
     * Parse parse dirty string with digit symbols return big decimal number.
     *
     * @param expectedValue the expected value
     * @param originValue   the origin value
     */
    @MethodSource("getDirtyStringsForTestParamsProvider")
    @ParameterizedTest
    public void parse_parseDirtyStringWithDigitSymbols_returnBigDecimalNumber(Double expectedValue, String originValue) {
        BigDecimal actualValue = NumberParser.newInstance().parse(BigDecimal.class, originValue);

        assertTrue(
                actualValue.doubleValue() == expectedValue.doubleValue(),
                String.format("Expected %s, but was %s", expectedValue, actualValue)
        );
    }
}
