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

import java.net.URL;

/**
 * The type Abstract test case loader.
 *
 * @author Alexander A. Kropotin
 * @project java -number-extension
 * @created 2021 -12-03 21:27 <p>
 */
public abstract class AbstractTestCaseLoader implements TestCaseLoader {

    /**
     * Instantiates a new Abstract test case loader.
     */
    AbstractTestCaseLoader() {
    }

    /**
     * Gets resource.
     *
     * @param resourcePathString the resource path string
     * @return the resource
     */
    protected URL getResource(String resourcePathString) {
        URL resource = getClass().getClassLoader().getResource(resourcePathString);
        if (resource == null)
            throw new IllegalArgumentException("The resource '" + resourcePathString + "' not found!");

        return resource;
    }
}
