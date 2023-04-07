package com.lvl.isbnTools;

import com.lvl.isbnTools.model.Book;
import com.lvl.isbnTools.service.ExternalISBNDataService;

public class StockManager {

	private ExternalISBNDataService webService;
	private ExternalISBNDataService databaseService;
	
	public StockManager(ExternalISBNDataService webService, ExternalISBNDataService databaseService) {
		super();
		this.webService = webService;
		this.databaseService = databaseService;
	}

	public String getLocatorCode(String isbn) {
	
		StringBuilder code = new StringBuilder();
		
		Book book = databaseService.lookup(isbn); 
		if (book == null) book = webService.lookup(isbn);
		
		code.append(isbn.substring(isbn.length()-4));
		code.append(book.getAuthor().toUpperCase().charAt(0));
		
		String[] wordsInTitle = book.getTitle().split("\\s+"); 
		code.append(wordsInTitle.length);
		
		return code.toString();
	}

}
