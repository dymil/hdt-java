/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.rdfhdt.hdtjena.junit;


import org.apache.jena.arq.junit.manifest.Manifest;
import org.apache.jena.arq.junit.manifest.ManifestEntry;
import org.apache.jena.arq.junit.sparql.tests.QueryTestItem;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.sparql.junit.QueryTestException;
import org.apache.jena.sparql.vocabulary.TestManifest;
import org.apache.jena.sparql.vocabulary.TestManifestUpdate_11;
import org.apache.jena.sparql.vocabulary.TestManifestX;
import org.apache.jena.sparql.vocabulary.TestManifest_11;
import org.apache.jena.util.junit.TestUtils;

import java.util.*;

public class ScriptTestFactory {
    private static final List<Resource> IGNORE = Arrays.asList(
            TestManifest.PositiveSyntaxTest,
            TestManifest_11.PositiveSyntaxTest11,
            TestManifestX.PositiveSyntaxTestARQ,
            TestManifest.NegativeSyntaxTest,
            TestManifest_11.NegativeSyntaxTest11,
            TestManifestX.NegativeSyntaxTestARQ,
            TestManifest_11.PositiveUpdateSyntaxTest11,
            TestManifest_11.NegativeUpdateSyntaxTest11,
            TestManifestUpdate_11.UpdateEvaluationTest,
            TestManifest_11.UpdateEvaluationTest,
            TestManifestX.TestSerialization,
            TestManifestX.TestSurpressed,
            TestManifest_11.CSVResultFormatTest);

    private final String groupName;

    public ScriptTestFactory(String groupName) {
        this.groupName = Objects.requireNonNull(groupName, "groupName");
    }

    public List<ScriptTest> load(String manifestUri) {
        List<ScriptTest> tests = new ArrayList<>();

        Manifest m = Manifest.parse(manifestUri);
        String manifestName = join(groupName, TestUtils.safeName(m.getName() != null ? m.getName() : manifestUri));

        // Recur
        for (Iterator<String> iter = m.includedManifests(); iter.hasNext(); ) {
            String n = iter.next();
            tests.addAll(new ScriptTestFactory(manifestName).load(n));
        }

        for (ManifestEntry entry : m.entries() ) {
            Resource testType = entry.getTestType();
            if ( testType == null )
                testType = TestManifest.QueryEvaluationTest;

            String testName = join(groupName, entry.getName());

            if (IGNORE.contains(testType)) {
                continue;
            }

            if (HDTQueryTest.accepts(testType)) {

                QueryTestItem item = QueryTestItem.create(entry.getEntry(), TestManifest.QueryEvaluationTest);

                ScriptTest test = new HDTQueryTest(testName, item);
                if (test != null) {
                    tests.add(test);
                }

            } else
                throw new QueryTestException("Unknown testType: " + testType);
        }
        return tests;
    }


    private static String join(String parent, String child) {
        return parent != null ? parent + " / " + child : child;
    }
}
