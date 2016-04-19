package com.noodles.lynn.xml_parse;

import com.noodles.lynn.xml_parse.model.Book;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Lynn on 2016/4/19.
 */
public class Sax_BookParser implements BookParser  {

    @Override
    public List<Book> bookParse(InputStream is) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        MyHandler handler = new MyHandler();
        parser.parse(is,handler);

        return handler.getBooks();
    }
    /**
     * 序列化集合对象，方便输出Xml格式*/
    @Override
    public String serialize(List<Book> books) {
        return null;
    }


    /**
     * 重写DefaultHandler的方法*/
    private class MyHandler extends DefaultHandler {
        private List<Book> books;
        private Book book;
        private StringBuilder builder;
        /**
         * 获取解析后的Book对象集合*/
        public List<Book> getBooks(){
            return books;
        };

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            books = new ArrayList<>();
            builder = new StringBuilder();

        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if(localName.equals("book")){
                book = new Book();
            }
            builder.setLength(0);// 将字符长度设置为0，方便重新开始读取元素。
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            builder.append(ch,start,length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if(localName.equals("id")){
                book.setId(Integer.parseInt(builder.toString()));
            }
            else if(localName.equals("name")){
                book.setName(builder.toString());
            }
            else if (localName.equals("price")){
                book.setPrice(Float.parseFloat(builder.toString()));
            }
            else if(localName.equals("book")){
                books.add(book);}
        }
    }
}
