package com.lvl.isbnTools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.lvl.isbnTools.service.BookDatabaseStub;
import com.lvl.isbnTools.service.ExternalISBNDataStub;

class StockManagerTest {

	@Test
	void should_ReturnCorrectLocatorCode_When_ValidISBN() {
		
		// given
		StockManager db = new StockManager(new ExternalISBNDataStub(), new BookDatabaseStub());
		String isbn = "012000030X";
		String expected = "030XC5";
		
		// when
		String locatorCode = db.getLocatorCode(isbn);
		
		// then
		assertEquals(expected, locatorCode);
		
	}
	
	@Test
	void should_UseDatabaseVersion_When_ISBNInDatabase() {
		fail("Not implemented");
	}
	
	@Test
	void should_UseCloudVersion_When_ISBNNotInDatabase() {
		fail("Not implemented");
	}

}
