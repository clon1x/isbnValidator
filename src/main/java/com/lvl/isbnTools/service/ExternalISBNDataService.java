package com.lvl.isbnTools.service;

import com.lvl.isbnTools.model.Book;

public interface ExternalISBNDataService {
	public Book lookup(String isbn);
}
