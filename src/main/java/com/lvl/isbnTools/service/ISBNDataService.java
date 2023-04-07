package com.lvl.isbnTools.service;

import com.lvl.isbnTools.model.Book;

public interface ISBNDataService {
	public Book lookup(String isbn);
}
