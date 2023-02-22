package com.adsh.library;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.TransitionOptions;

import java.time.temporal.Temporal;
import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    public static final String BOOK_ID_KEY = "bookId";
    private TextView txtBookName, txtAuthor, txtPages, txtDescription;
    private Button btnAddToWantToRead, btnAddToAlreadyRead, btnAddToCurrentlyReading, btnAddToFavorite, btnBookWebsite;
    private ImageView bookImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();

//        //TODO Getthe data from recyclerview in here
//        Book book = new Book(1, "Android", "Sihan Adi", 1350,
//                "https://pictures.abebooks.com/isbn/9781977540096-us.jpg",
//                "Learning Android", "Long desc");

        Intent intent = getIntent();
        if (intent != null) {
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (bookId != -1) {
                Book incomingBook = Utils.getUtils(this).getBookByID(bookId);
                if (incomingBook != null) {
                    setBook(incomingBook);

                    handleAlreadyRead(incomingBook);
                    handleWantToRead(incomingBook);
                    handleCurrentlyReading(incomingBook);
                    handleFavorite(incomingBook);
                    handleBookWebsite(incomingBook);

                }
            }
        }

    }

    private void handleBookWebsite(Book incomingBook) {
        btnBookWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BookActivity.this);
                builder.setTitle(incomingBook.getName());
                builder.setMessage("If you want to buy this book.\n" +
                        "Click this website");
                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("Visit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(BookActivity.this, WebsiteActivity.class);
                        intent.putExtra("url", incomingBook.getBookUrl());
                        startActivity(intent);
                    }
                });
                builder.create().show();
            }
        });
    }

    private void handleFavorite(Book incomingBook) {
        ArrayList<Book> FavoriteBooks = Utils.getUtils(this).getFavoriteBooks();
        boolean existFavoriteBooks = false;
        for (Book book : FavoriteBooks) {
            if (book.getId() == incomingBook.getId()) {
                existFavoriteBooks = true;
            }
        }
        if (existFavoriteBooks) {
            btnAddToFavorite.setEnabled(false);
        } else {
            btnAddToFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getUtils(BookActivity.this).addToFavoriteBooks(incomingBook)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, FavoriteActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something Wrong Happened, Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleCurrentlyReading(Book incomingBook) {
        ArrayList<Book> CurrentlyReadingBooks = Utils.getUtils(this).getCurrentlyReadingBooks();
        boolean existCurrentlyReadingBooks = false;
        for (Book book : CurrentlyReadingBooks) {
            if (book.getId() == incomingBook.getId()) {
                existCurrentlyReadingBooks = true;
            }
        }
        if (existCurrentlyReadingBooks) {
            btnAddToCurrentlyReading.setEnabled(false);
        } else {
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getUtils(BookActivity.this).addToCurrentlyReadingBooks(incomingBook)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something Wrong Happened, Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleWantToRead(final Book incomingBook) {
        ArrayList<Book> WantToReadBooks = Utils.getUtils(this).getWantToReadBooks();
        boolean existWantToReadBooks = false;
        for (Book book : WantToReadBooks) {
            if (book.getId() == incomingBook.getId()) {
                existWantToReadBooks = true;
            }
        }
        if (existWantToReadBooks) {
            btnAddToWantToRead.setEnabled(false);
        } else {
            btnAddToWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getUtils(BookActivity.this).addToWantToReadBooks(incomingBook)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, WantoReadActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something Wrong Happened, Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleAlreadyRead(final Book incomingBook) {
        ArrayList<Book> alreadyReadBooks = Utils.getUtils(this).getAlreadyReadBooks();
        boolean existAlreadyReadBooks = false;
        for (Book book : alreadyReadBooks) {
            if (book.getId() == incomingBook.getId()) {
                existAlreadyReadBooks = true;
            }
        }
        if (existAlreadyReadBooks) {
            btnAddToAlreadyRead.setEnabled(false);
        } else {
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getUtils(BookActivity.this).addToAlreadyReadBooks(incomingBook)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something Wrong Happened, Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setBook(Book book) {
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap()
                .load(book.getImageUrl())
                .into(bookImage);
    }

    private void initViews() {
        txtBookName = findViewById(R.id.bookNameText);
        txtAuthor = findViewById(R.id.AuthorText);
        txtPages = findViewById(R.id.pageText);
        txtDescription = findViewById(R.id.longDesriptionTxt);
        btnAddToWantToRead = findViewById(R.id.btnAddToWantToRead);
        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyRead);
        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToFavorite = findViewById(R.id.btnAddToFavorities);
        bookImage = findViewById(R.id.bookImage);
        btnBookWebsite = findViewById(R.id.btnBookWebsit);


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
}