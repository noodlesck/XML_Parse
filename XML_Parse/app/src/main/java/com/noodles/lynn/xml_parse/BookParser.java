package com.noodles.lynn.xml_parse;

import com.noodles.lynn.xml_parse.model.Book;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Lynn on 2016/4/19.
 */
public interface BookParser {
    /**
    * 解析输入流，得到book对象集合
    * */
  public List<Book> bookParse(InputStream is) throws Exception;
  /**
  * 序列化对象集合，得到xml形式字符串
  * */
  public String serialize(List<Book> books) throws Exception;
}
