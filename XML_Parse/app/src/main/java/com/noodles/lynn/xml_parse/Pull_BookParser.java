package com.noodles.lynn.xml_parse;

import android.util.Xml;

import com.noodles.lynn.xml_parse.model.Book;

import org.xml.sax.Parser;
import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lynn on 2016/4/19.
 */
public class Pull_BookParser implements BookParser {
    @Override
    public List<Book> bookParse(InputStream is) throws Exception {
        List<Book> books = null;
        Book book = null;

        //      XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        //      XmlPullParser parser = factory.newPullParser();

        XmlPullParser pullParser = Xml.newPullParser();//android.util.Xml 创建解析器实例
        pullParser.setInput(is,"UTF-8");//设置输入流，编码格式。

        int eventType = pullParser.getEventType(); //返回int 事件类型
        while(eventType != XmlPullParser.END_DOCUMENT){
            switch(eventType){
                case XmlPullParser.START_DOCUMENT:
                    books = new ArrayList<Book>();
                    break;
                case XmlPullParser.START_TAG:
                    if(pullParser.getName().equals("book")){
                        book = new Book();
                    }else if (pullParser.getName().equals("id")){
                        eventType = pullParser.next();
                        book.setId(Integer.parseInt(pullParser.getText()));
                    }else if (pullParser.getName().equals("name")){
                        eventType = pullParser.next();
                        book.setName(pullParser.getText());
                    }else if (pullParser.getName().equals("price")){
                        eventType = pullParser.next();
                        book.setPrice(Float.parseFloat(pullParser.getText()));
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if (pullParser.getName().equals("book")){
                        books.add(book);
                        book = null;
                    }
                    break;
            }
            eventType = pullParser.next();
        }

        return books;
    }

    @Override
    public String serialize(List<Book> books) throws Exception {
        return null;
    }
}
