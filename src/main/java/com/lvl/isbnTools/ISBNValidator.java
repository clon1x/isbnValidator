package com.lvl.isbnTools;

public class ISBNValidator {

	public boolean isValidISBN(String isbn) {
		if (isbn.length() == 10)
			return isValidISBN10(isbn);
		if (isbn.length() == 13)
			return isValidISBN13(isbn);
		
		throw new NumberFormatException(Messages.getString("ISBNValidator.0")); //$NON-NLS-1$
	}

	private static boolean isValidISBN10(String isbn) {

		int total = 0;
		isbn = isbn.toUpperCase(); 
		
		for (int i = 0; i < 10; i++) {
			char c = isbn.charAt(i);
			if (Character.isDigit(c)) {
				total += (10-i) * Character.getNumericValue(c);
			} else if (c == 'X' && i == 9){
				total += 10;
			} else {
				throw new NumberFormatException(Messages.getString("ISBNValidator.1")); //$NON-NLS-1$
			}
		}

		return (total % 11 == 0);
	}

	private boolean isValidISBN13(String isbn) {
		
		int total = 0, digit = 0;
		
		for (int i = 0; i < 13; i++) {
			char c = isbn.charAt(i);
			if (!Character.isDigit(c)) throw new NumberFormatException(Messages.getString("ISBNValidator.2")); //$NON-NLS-1$
			
			digit = Character.getNumericValue(c);
			total += digit * (1 + 2 * (i % 2));
		}
		
		return (total % 10 == 0);
	}

}
