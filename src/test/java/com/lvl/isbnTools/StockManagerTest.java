package com.lvl.isbnTools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.lvl.isbnTools.model.Book;
import com.lvl.isbnTools.service.BookDatabaseStub;
import com.lvl.isbnTools.service.BookWebServiceDataStub;
import com.lvl.isbnTools.service.ISBNDataService;

class StockManagerTest {

	@Test
	void should_ReturnCorrectLocatorCode_When_ValidISBN() {

		// given
		StockManager db = new StockManager(new BookWebServiceDataStub(), new BookDatabaseStub());
		String isbn = "012000030X";
		String expected = "030XC5";

		// when
		String locatorCode = db.getLocatorCode(isbn);

		// then
		assertEquals(expected, locatorCode);

	}

	@Test
	void should_UseDatabaseVersion_When_ISBNInDatabase() {

		// given
		String isbn = "012000030X";
		String expected = "030XC5";
		ISBNDataService databaseServiceMock = Mockito.mock(ISBNDataService.class);
		ISBNDataService webServiceMock = Mockito.mock(ISBNDataService.class);
		StockManager manager = new StockManager(webServiceMock, databaseServiceMock);

		Mockito.when(databaseServiceMock.lookup("012000030X"))
				.thenReturn(new Book("012000030X", "The Last of the Mohicans", "Cooper, James Fenimore"));

		// when
		String locatorCode = manager.getLocatorCode(isbn);

		// then
		assertEquals(expected, locatorCode);

	}

	@Test
	void should_UseCloudVersion_When_ISBNNotInDatabase() {
		fail("Not implemented");
	}

}
