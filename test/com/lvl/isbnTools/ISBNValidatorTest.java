package com.lvl.isbnTools;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ISBNValidatorTest {

	@Test
	void should_ReturnTrue_When_ISBNIsValid() {

		// given
		int isbn = 1719587213;
		ISBNValidator validator = new ISBNValidator();

		// when
		boolean isValid = validator.isValidISBN(isbn);

		// then
		assertTrue(isValid);
	}

	@Test
	void should_ReturnFalse_When_ISBNIsNotValid() {

		// given
		int isbn = 1719587214;
		ISBNValidator validator = new ISBNValidator();

		// when
		boolean isValid = validator.isValidISBN(isbn);

		// then
		assertFalse(isValid);
	}
	
	
	
}
