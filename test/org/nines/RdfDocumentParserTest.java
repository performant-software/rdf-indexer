/** 
 *  Copyright 2007 Applied Research in Patacriticism and the University of Virginia
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 **/
package org.nines;

import junit.framework.TestCase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.openrdf.rio.StatementHandlerException;
import org.openrdf.rio.ParseException;

public class RdfDocumentParserTest extends TestCase {
  private ErrorReport errorReport;
	
  public void setUp() throws IOException {
	errorReport = new ErrorReport(new File("test_data","test_report.txt"));
  }

  public void testBadNinesElement() throws StatementHandlerException, IOException, ParseException {
    parse("test_data/bad_nines_element.rdf");
    assertEquals(1, errorReport.getErrorCount());
  }

  public void testInvalidXml() throws IOException, StatementHandlerException, ParseException {
    parse("test_data/invalid_xml.rdf");
    assertEquals(1, errorReport.getErrorCount());
  }

  public void testBadDate() throws IOException, StatementHandlerException, ParseException {
    parse("test_data/bad_date.rdf");
    assertEquals(1, errorReport.getErrorCount());
  }

  public void testRole() throws IOException, StatementHandlerException, ParseException {
    parse("test_data/role_test.rdf");
    assertEquals(0, errorReport.getErrorCount());
  }

  private HashMap<String, HashMap<String,ArrayList<String>>> parse(String filename) throws StatementHandlerException, IOException, ParseException {
    return RdfDocumentParser.parse(new File(System.getProperty("test.data.dir"),filename), errorReport, new LinkCollector(), new RDFIndexerConfig() );
  }
}
