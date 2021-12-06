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

import com.sun.jdi.ByteType;
import org.java.number.extension.parsing.base.DirectionalGraph;
import org.java.number.extension.parsing.base.Graph;
import org.java.number.extension.parsing.internal.type.BasicNumberType;
import org.java.number.extension.parsing.internal.type.NumberType;
import org.java.number.extension.parsing.internal.type.NumberTypeResolver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
@DisplayName("The NumberTypeResolverTest test cases")
public class NumberTypeResolverTest {

    static final NumberTypeResolver numberTypeResolver = new NumberTypeResolver();

    @DisplayName("[positive]: test compareExpressiveness method type < otherType")
    @Test
    public void compareExpressiveness_whenFirstTypeIsLessExpressive_thenReturnNegativeOne() {
        assertTrue(numberTypeResolver.compareExpressiveness(Byte.TYPE, Double.TYPE) == -1);
    }

    @DisplayName("[positive]: test compareExpressiveness method type > otherType")
    @Test
    public void compareExpressiveness_whenFirstTypeIsMoreExpressive_thenReturnOne() {
        assertTrue(numberTypeResolver.compareExpressiveness(Double.TYPE, Byte.TYPE) == 1);
    }

    @DisplayName("[positive]: test compareExpressiveness method type == otherType")
    @Test
    public void compareExpressiveness_whenFirstTypeIsEqualeExpressive_thenReturnZero() {
        assertTrue(numberTypeResolver.compareExpressiveness(Byte.TYPE, Byte.TYPE) == 0);
    }
}
