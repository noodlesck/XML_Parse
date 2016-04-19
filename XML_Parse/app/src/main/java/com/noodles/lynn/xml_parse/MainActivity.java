package com.noodles.lynn.xml_parse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.noodles.lynn.xml_parse.model.Book;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "xml";
    private Sax_BookParser parser; //SAX 解析
   // private Pull_BookParser parser; //Pull 解析
    private List<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button readXml = (Button) findViewById(R.id.btnRead);
        readXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputStream is = getAssets().open("book.xml");
                    parser = new Sax_BookParser();
                   // parser = new Pull_BookParser();
                    books = parser.bookParse(is);
                    for(Book book :books){
                        Log.i(TAG,book.toString());
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
}