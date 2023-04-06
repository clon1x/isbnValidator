package com.lvl.isbnTools;

import com.lvl.isbnTools.model.Book;
import com.lvl.isbnTools.service.ExternalISBNDataService;

public class StockManager {

	private ExternalISBNDataService isbnLookupService;
	
	public StockManager(ExternalISBNDataService isbnLookupService) {
		super();
		this.isbnLookupService = isbnLookupService;
	}

	public String getLocatorCode(String isbn) {
		StringBuilder code = new StringBuilder();
		Book book = isbnLookupService.lookup(isbn);
		
		code.append(isbn.substring(isbn.length()-4));
		code.append(book.getAuthor().toUpperCase().charAt(0));
		
		String[] wordsInTitle = book.getTitle().split("\\s+"); 
		code.append(wordsInTitle.length);
		
		return code.toString();
	}

}
