package com.douk.dao;

import com.douk.pojo.Books;

import java.util.List;

public interface BookMapper {
//    增加
    int addBook(Books books);
//    删除一本
    int deleteBookById(int id);
//    更新一本
    int updateBook(Books books);
//    查询一本
    Books selectBookById(int id);
//    查询全部
    List<Books> queryAllBooks();

}

