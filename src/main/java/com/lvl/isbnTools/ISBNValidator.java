package com.lvl.isbnTools;

public class ISBNValidator {

	public boolean isValidISBN(String isbn) {
		
		if (isbn.length() != 10) throw new NumberFormatException(Messages.getString("ISBNValidator.0")); //$NON-NLS-1$
		
		int total = 0;
		isbn = isbn.toUpperCase(); 
		
		for (int i = 0; i < 10; i++) {
			char c = isbn.charAt(i);
			if (!Character.isDigit(c)) {
				total += (10-i) * getValue(c);
			} else {
				total += (10-i) * Character.getNumericValue(c);
			}
		}
		
		return (total % 11 == 0);
	}

	private int getValue(char c) {
		if (c == 'X') return 10;
		throw new NumberFormatException(Messages.getString("ISBNValidator.1")); //$NON-NLS-1$
	}
	
}
