package com.lvl.isbnTools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.lvl.isbnTools.model.Book;
import com.lvl.isbnTools.service.BookDatabaseStub;
import com.lvl.isbnTools.service.BookWebServiceDataStub;
import com.lvl.isbnTools.service.ISBNDataService;

class StockManagerTest {

	private static ISBNDataService databaseServiceMock;
	private static ISBNDataService webServiceMock;
	private static StockManager manager;

	@BeforeEach
	void setup() {
		databaseServiceMock = Mockito.mock(ISBNDataService.class);
		webServiceMock = Mockito.mock(ISBNDataService.class);
		manager = new StockManager(webServiceMock, databaseServiceMock);
	}
	
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
		Mockito.when(databaseServiceMock.lookup("012000030X"))
		.thenReturn(new Book("012000030X", "The Last of the Mohicans", "Cooper, James Fenimore"));

		// when
		manager.getLocatorCode(isbn);

		// then
		Mockito.verify(databaseServiceMock, times(1)).lookup("012000030X");
		Mockito.verify(webServiceMock, times(0)).lookup("012000030X");

	}

	@Test
	void should_UseCloudVersion_When_ISBNNotInDatabase() {

		// given
		String isbn = "012000030X";
		Mockito.when(webServiceMock.lookup("012000030X"))
		.thenReturn(new Book("012000030X", "The Last of the Mohicans", "Cooper, James Fenimore"));

		// when
		manager.getLocatorCode(isbn);

		// then
		Mockito.verify(databaseServiceMock, times(1)).lookup("012000030X");
		Mockito.verify(webServiceMock, times(1)).lookup("012000030X");

	}

}
