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
package utils;

import java.util.List;

/**
 * The interface Test case loader.
 *
 * @author Alexander A. Kropotin
 * @project java -number-extension
 * @created 2021 -12-03 21:23 <p>
 */
public interface TestCaseLoader {

    /**
     * Load list.
     *
     * @param resourcePathString the resource path string
     * @return the list
     */
    List<List<Object>> load(String resourcePathString);
}
