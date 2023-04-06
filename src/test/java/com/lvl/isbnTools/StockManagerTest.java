package com.lvl.isbnTools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StockManagerTest {

	@Test
	void should_ReturnCorrectLocatorCode_When_ValidISBN() {
		
		// given
		StockManager db = new StockManager();
		String isbn = "012000030X";
		String expected = "030XC5";
		
		// when
		String locatorCode = db.getLocatorCode(isbn);
		
		// then
		assertEquals(expected, locatorCode);
		
	}

}
