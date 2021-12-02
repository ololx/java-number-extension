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

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * The type Basic number type taxonomy.
 *
 * @param <T> the type parameter
 * @author Alexander A. Kropotin
 * @project java -number-extension
 * @created 2021 -12-02 13:32 <p>
 */
public class BasicNumberTypeTaxonomy<T extends NumberType> implements NumberTypeTaxonomy<T> {

    private Map<T, NumberTypeNode> numberTypeNodeMap;

    {
        numberTypeNodeMap = new HashMap<>();
    }

    /**
     * Instantiates a new Basic number type taxonomy.
     *
     * @param numberTypeNodeMap the number type node map
     */
    public BasicNumberTypeTaxonomy(NumberTypeNode<T>... numberTypeNodeMap) {
        if (numberTypeNodeMap == null) return;
        Arrays.stream(numberTypeNodeMap).forEach(node -> this.numberTypeNodeMap.put(node.getNumberType(), node));
    }

    public boolean isSubNumberTypeOf(T numberType, T superNumberType) {
        if (!this.numberTypeNodeMap.containsKey(numberType))
            return false;

        return this.numberTypeNodeMap.get(numberType).getSuperNumberType().equals(superNumberType);
    }

    public boolean isSuperNumberTypeOf(T numberType, T subNumberType) {
        if (!this.numberTypeNodeMap.containsKey(numberType))
            return false;

        return this.numberTypeNodeMap.get(numberType).getSubNumberType().equals(subNumberType);
    }
}