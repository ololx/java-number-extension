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
package org.java.number.extension.parsing.internal.type;

/**
 * The interface Number type taxonomy.
 *
 * @param <T> the type parameter
 * @author Alexander A. Kropotin
 * @project java -number-extension
 * @created 2021 -12-02 11:32 <p>
 */
public interface NumberTypeTaxonomy<T extends NumberType> {

    /**
     * The type Number type node.
     *
     * @param <T> the type parameter
     */
    public class NumberTypeNode<T extends NumberType> {

        private final T numberType;

        private T superNumberType;

        private T subNumberType;

        /**
         * Instantiates a new Number type node.
         *
         * @param numberType the number type
         */
        public NumberTypeNode(T numberType) {
            this(numberType, null, null);
        }

        /**
         * Instantiates a new Number type node.
         *
         * @param numberType      the number type
         * @param subNumberType   the sub number type
         * @param superNumberType the super number type
         */
        public NumberTypeNode(T numberType, T subNumberType, T superNumberType) {
            if (numberType == null)
                throw new NullPointerException("The number type couldn't be null");
            this.numberType = numberType;

            this.subNumberType = subNumberType;
            this.superNumberType = superNumberType;
        }

        /**
         * Gets number type.
         *
         * @return the number type
         */
        public T getNumberType() {
            return this.numberType;
        }

        /**
         * Gets sub number type.
         *
         * @return the sub number type
         */
        public T getSubNumberType() {
            return this.subNumberType != null ? this.subNumberType : this.numberType;
        }

        /**
         * Gets super number type.
         *
         * @return the super number type
         */
        public T getSuperNumberType() {
            return this.superNumberType != null ? this.superNumberType : this.numberType;
        }
    }

    /**
     * Is sub number type of boolean.
     *
     * @param numberType      the number type
     * @param superNumberType the super number type
     * @return the boolean
     */
    boolean isSubNumberTypeOf(T numberType, T superNumberType);

    /**
     * Is super number type of boolean.
     *
     * @param numberType    the number type
     * @param subNumberType the sub number type
     * @return the boolean
     */
    boolean isSuperNumberTypeOf(T numberType, T subNumberType);
}