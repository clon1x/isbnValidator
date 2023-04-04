package com.lvl.isbnTools;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class ISBNValidatorTest {
	
	private ISBNValidator validator;
	
	@BeforeEach
	void setup() {
		validator = new ISBNValidator();
	}

	@Nested
	class IsValidISBNTests {
	
		@ParameterizedTest(name = "isbn:{0}")
		@CsvFileSource(resources = "/validIsbn10Codes.csv")
		void should_ReturnTrue_When_ISBNIsValid(String isbnCandidate) {
	
			// given
			String isbn = isbnCandidate;
			ISBNValidator validator = new ISBNValidator();
	
			// when
			boolean isValid = validator.isValidISBN(isbn);
	
			// then
			assertTrue(isValid);
		}
	
		@ParameterizedTest(name = "isbn:{0}")
		@CsvFileSource(resources = "/invalidIsbn10Codes.csv")
		void should_ReturnFalse_When_ISBNIsNotValid() {
	
			// given
			String isbn = "1719587214";
	
			// when
			boolean isValid = validator.isValidISBN(isbn);
	
			// then
			assertFalse(isValid);
		}
		
		@Test
		void should_ThrowError_When_ISBNHasLessThan10Digits() {
			
			// given
			String isbn = "171958721";
			
			// when 
			Executable checkValid = () -> validator.isValidISBN(isbn);
			
			// then
			assertThrows(NumberFormatException.class, checkValid);
		}
	
	}
	
}
