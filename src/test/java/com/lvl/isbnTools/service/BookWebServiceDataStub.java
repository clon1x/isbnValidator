package com.lvl.isbnTools.service;

import java.util.Map;
import java.util.TreeMap;

import com.lvl.isbnTools.model.Book;

public class BookWebServiceDataStub implements ISBNDataService {

	private Map<String, Book> books;
	
	public BookWebServiceDataStub() {
		books = new TreeMap<>();
		books.put("012000030X", new Book("012000030X", "The last of the Mohicans", "Cooper, James Fenimore"));
	}

	@Override
	public Book lookup(String isbn) {
		if (books.containsKey(isbn)) {
			return new Book(isbn, "The last of the Mohicans", "Cooper, James Fenimore");			
		}
		return null;
	}

}
