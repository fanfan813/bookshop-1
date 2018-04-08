package com.book.service;

import org.springframework.web.multipart.MultipartFile;

import com.book.domain.Book;
import com.book.util.Pager;

public interface BookService {

	Pager query(Book book,int pageNum,int pageSize,String orderBy);

	Book findById(String id);

	Pager queryhotBooks();

	void save(Book book, MultipartFile multipartFile);

	void remove(String bid);

}
