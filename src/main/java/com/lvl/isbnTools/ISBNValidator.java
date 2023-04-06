package com.lvl.isbnTools;

public class ISBNValidator {

	private static final int ISBN_10_LENGTH = 10;
	private static final int ISBN_10_DIVISOR = 11;
	private static final int ISBN_10_X_VALUE = 10;
	private static final int ISBN_13_LENGTH = 13;
	private static final int ISBN_13_DIVISOR = 10;

	public boolean isValidISBN(String isbn) {
		if (isbn.length() == ISBN_10_LENGTH)
			return isValidISBN10(isbn);
		if (isbn.length() == ISBN_13_LENGTH)
			return isValidISBN13(isbn);
		
		throw new NumberFormatException(Messages.getString("ISBNValidator.0")); //$NON-NLS-1$
	}

	private static boolean isValidISBN10(String isbn) {

		int total = 0;
		isbn = isbn.toUpperCase(); 
		
		for (int i = 0; i < ISBN_10_LENGTH; i++) {
			char c = isbn.charAt(i);
			if (Character.isDigit(c)) {
				total += (ISBN_10_LENGTH-i) * Character.getNumericValue(c);
			} else if (c == 'X' && i == 9){
				total += ISBN_10_X_VALUE;
			} else {
				throw new NumberFormatException(Messages.getString("ISBNValidator.1")); //$NON-NLS-1$
			}
		}

		return (total % ISBN_10_DIVISOR == 0);
	}

	private boolean isValidISBN13(String isbn) {
		
		int total = 0, digit = 0;
		
		for (int i = 0; i < ISBN_13_LENGTH; i++) {
			char c = isbn.charAt(i);
			if (!Character.isDigit(c)) throw new NumberFormatException(Messages.getString("ISBNValidator.2")); //$NON-NLS-1$
			
			digit = Character.getNumericValue(c);
			total += digit * (1 + 2 * (i % 2));
		}
		
		return (total % ISBN_13_DIVISOR == 0);
	}

}
