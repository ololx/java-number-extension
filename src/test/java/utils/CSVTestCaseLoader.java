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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Csv test case loader.
 *
 * @author Alexander A. Kropotin
 * @project java -number-extension
 * @created 2021 -12-03 21:24 <p>
 */
public class CSVTestCaseLoader extends AbstractTestCaseLoader {

    /**
     * Instantiates a new Csv test case loader.
     */
    public CSVTestCaseLoader() {
        super();
    }

    @Override
    public List<List<Object>> load(String resourcePathString) {
        URL dirtyStringsCasesResource = this.getResource(resourcePathString);

        List<List<Object>> dirtyStringsCases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(dirtyStringsCasesResource.openStream()))) {
            String dirtyStringCase;
            while ((dirtyStringCase = br.readLine()) != null) {
                String[] caseValues = dirtyStringCase.split(";");
                dirtyStringsCases.add(Arrays.asList(caseValues));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dirtyStringsCases;
    }
}
