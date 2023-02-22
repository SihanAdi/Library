package com.adsh.library;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {
    private RecyclerView booksRecyclerView;
    private BookRecViewAdapter bookRecViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

//        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bookRecViewAdapter = new BookRecViewAdapter(this, "allBooks");
        booksRecyclerView = findViewById(R.id.booksRecVie);

        booksRecyclerView.setAdapter(bookRecViewAdapter);
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        bookRecViewAdapter.setBooks(Utils.getUtils(this).getAllBooks());

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //    @Override
//    public void finish() {
//        super.finish();
//        overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
//    }
}