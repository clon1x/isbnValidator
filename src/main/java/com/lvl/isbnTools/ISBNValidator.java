package com.lvl.isbnTools;

public class ISBNValidator {

	public boolean isValidISBN(String isbn) {
		
		if (isbn.length() != 10) throw new NumberFormatException(); 
		
		int total = 0;
		
		for (int i = 0; i < 10; i++) {
			total += (10-i)*(int) isbn.charAt(i);
		}
		
		return (total % 11 == 0);
	}
	
}
