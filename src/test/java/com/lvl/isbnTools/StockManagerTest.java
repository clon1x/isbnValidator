package com.lvl.isbnTools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.lvl.isbnTools.model.Book;
import com.lvl.isbnTools.service.ISBNDataService;

class StockManagerTest {

	private static final String EXPECTED_LOCATOR_CODE = "030XC5";
	private static final String ISBN_CODE = "012000030X";
	private static ISBNDataService databaseServiceMock;
	private static ISBNDataService webServiceMock;
	private static StockManager stockManager;

	@BeforeEach
	void setup() {
		databaseServiceMock = Mockito.mock(ISBNDataService.class);
		webServiceMock = Mockito.mock(ISBNDataService.class);
		stockManager = new StockManager(webServiceMock, databaseServiceMock);

		Mockito.when(webServiceMock.lookup(ISBN_CODE))
		.thenReturn(new Book(ISBN_CODE, "The Last of the Mohicans", "Cooper, James Fenimore"));
	}
	
	@Test
	void should_ReturnCorrectLocatorCode_When_ValidISBN() {

		// given
		String isbn = ISBN_CODE;
		String expected = EXPECTED_LOCATOR_CODE;

		// when
		String locatorCode = stockManager.getLocatorCode(isbn);

		// then
		assertEquals(expected, locatorCode);

	}

	@Test
	void should_UseDatabaseVersion_When_ISBNInDatabase() {

		// given
		String isbn = ISBN_CODE;
		Mockito.when(databaseServiceMock.lookup(ISBN_CODE))
		.thenReturn(new Book(ISBN_CODE, "The Last of the Mohicans", "Cooper, James Fenimore"));

		// when
		stockManager.getLocatorCode(isbn);

		// then
		Mockito.verify(databaseServiceMock, times(1)).lookup(ISBN_CODE);
		Mockito.verify(webServiceMock, times(0)).lookup(Mockito.anyString());

	}

	@Test
	void should_UseCloudVersion_When_ISBNNotInDatabase() {

		// given
		String isbn = ISBN_CODE;
		
		// when
		stockManager.getLocatorCode(isbn);

		// then
		Mockito.verify(databaseServiceMock, times(1)).lookup(ISBN_CODE);
		Mockito.verify(webServiceMock, times(1)).lookup(ISBN_CODE);

	}

}
