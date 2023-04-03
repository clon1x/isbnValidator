package com.lvl.isbnTools;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class ISBNValidatorTest {

	@ParameterizedTest(name = "isbn:{0}")
	@CsvFileSource(resources = "/validIsbn10Codes.csv")
	void should_ReturnTrue_When_ISBNIsValid(long isbnCandidate) {

		// given
		long isbn = isbnCandidate;
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
