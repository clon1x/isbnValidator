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

}
