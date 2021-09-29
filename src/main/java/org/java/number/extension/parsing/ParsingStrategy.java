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

/**
 * @project java-number-extension
 * @created 2021-09-28 18:54
 * <p>
 * @author Alexander A. Kropotin
 */
public interface ParsingStrategy<T extends Number> {

    /**
     * Parse number from dirty string value.
     * This parse method have to:
     * 1 - get value as a {@Code string},
     * 2 - skip all non digit symbols
     * 3 - parse sign value {1, -1}, integer and fractional parts, and exponents values from e-notation
     * 4 - generate {@code Number} by this formula:
     * {@code x = (integer + fractional) * sign * sum(exponents)}
     *
     * @param value the dirty string
     * @return the number value from a dirty string
     */
    T apply(String value);
}
