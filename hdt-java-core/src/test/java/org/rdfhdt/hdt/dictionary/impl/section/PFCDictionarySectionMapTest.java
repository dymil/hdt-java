package org.rdfhdt.hdt.dictionary.impl.section;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.rdfhdt.hdt.exceptions.NotFoundException;
import org.rdfhdt.hdt.hdt.HDT;
import org.rdfhdt.hdt.hdt.HDTManager;
import org.rdfhdt.hdt.triples.IteratorTripleString;

import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;

public class PFCDictionarySectionMapTest {

	HDT hdt;

	@Before
	public void setUp() throws Exception {
//		hdt = HDTManager.generateHDT("data/test.nt", "http://example.org", RDFNotation.NTRIPLES, new HDTSpecification(), null);
	}

	@Test
	public void test() {
//
//		PFCDictionarySectionMap shareds = (PFCDictionarySectionMap) ((FourSectionDictionary)hdt.getDictionary()).shared;
//
//		System.out.println(shareds.getNumberOfElements());
//
//		int arr[] = { 1, 5,6,7, 62, 94, 105,106,107, 180 };
//
////		Iterator<Integer> itId = new ArrayIterator(arr);
////		Iterator<CharSequence> itStr = shareds.getSortedEntries(new ArrayIterator(arr));
//
//		Iterator<Integer> itId = new RangeIterator(10,100);
//		Iterator<CharSequence> itStr = shareds.getSortedEntries(new RangeIterator(10,100));
//
//		while(itStr.hasNext()) {
//			CharSequence str = itStr.next();
//
//			assertTrue(itId.hasNext());
//			int id = itId.next();
//			CharSequence str2 = hdt.getDictionary().idToString(id, TripleComponentRole.SUBJECT);
//
//			System.out.println(id+" Should be: "+str2+ " is "+str);
////			assertTrue(str.toString().equals(str2));
//		}
	}

}
