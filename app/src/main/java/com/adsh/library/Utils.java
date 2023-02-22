package com.adsh.library;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {
    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ_BOOK = "already_read_books";
    private static final String WANT_TO_READ_BOOKS = "want_to_read_books";
    private static final String CURRENTLY_READING_BOOKS = "currently_reading_books";
    private static final String FAVORITE_BOOKS = "favorite_books";
    private static Utils utils;
    private SharedPreferences sharedPreferences;
//    private static ArrayList<Book> allBooks;
//    private static ArrayList<Book> alreadyReadBooks;
//    private static ArrayList<Book> wantToReadBooks;
//    private static ArrayList<Book> currentlyReadingBooks;
//    private static ArrayList<Book> favoriteBooks;

    private Utils(Context context) {

        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);

        if (getAllBooks() == null) {
            initData();
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (getAlreadyReadBooks() == null) {
            editor.putString(ALREADY_READ_BOOK, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (getWantToReadBooks() == null) {
            editor.putString(WANT_TO_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (getCurrentlyReadingBooks() == null) {
            editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (getFavoriteBooks() == null) {
            editor.putString(FAVORITE_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }

    private void initData() {
        //TODO: add initial data

        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book(1, "Android", "Sihan Adi", 1350,
                "https://pictures.abebooks.com/isbn/9781977540096-us.jpg",
                "Learning Android", "Long Description",
                "https://www.amazon.com/Android-Studio-4-2-Development-Essentials/dp/1951442318/ref=asc_df_1951442318/?tag=hyprod-20&linkCode=df0&hvadid=509098801384&hvpos=&hvnetw=g&hvrand=11498235599796472119&hvpone=&hvptwo=&hvqmt=&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=9008163&hvtargid=pla-1334576655561&psc=1"));
        books.add(new Book(2, "Java", "AD", 1850,
                "https://horstmann.com/corejava/cj12v2.jpg",
                "Learning Java", "Long Description",
                "https://www.googleadservices.com/pagead/aclk?sa=L&ai=DChcSEwiCsK2fz6f9AhWJQ3IKHe5xCnYYABAPGgJxdQ&ohost=www.google.com&cid=CAESa-D2Bd60MQA9czC_7W3RLn699VDcOugq7SI509SBSs5GKzyQT3ygotQHagR4VCu4UE7pLOp0XIP0dqKF3frCjkkKY2frBwn70_QbAXDCsY_dH38lJWWPK5XqzTeD3-ZAa-mpwl4YD1eQGq5z&sig=AOD64_29MWs1vQ8YdqTTYEQieWvwjhluhA&ctype=5&q=&ved=2ahUKEwj1lKWfz6f9AhU3EVkFHW-ACyIQ9aACKAB6BAgGEA8&adurl="));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
        editor.commit();

    }


    public static Utils getUtils(Context context) {
        if (utils == null) {
            utils = new Utils(context);
            return utils;
        } else {
            return utils;
        }
    }

    public static void setUtils(Utils utils) {
        Utils.utils = utils;
    }

    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), type);
        return books;
    }


    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOK, null), type);
        return books;
    }


    public ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS, null), type);
        return books;
    }


    public ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS, null), type);
        return books;
    }


    public ArrayList<Book> getFavoriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVORITE_BOOKS, null), type);
        return books;
    }


    public Book getBookByID(int id) {
        ArrayList<Book> books = getAllBooks();
        if (books != null) {
            for (Book book : books) {
                if (book.getId() == id) {
                    return book;
                }
            }
        }

        return null;
    }

    public boolean addToAlreadyReadBooks(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (books != null) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOK);
                editor.putString(ALREADY_READ_BOOK, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToWantToReadBooks(Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if (books != null) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS);
                editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToCurrentlyReadingBooks(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (books != null) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToFavoriteBooks(Book book) {
        ArrayList<Book> books = getFavoriteBooks();
        if (books != null) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVORITE_BOOKS);
                editor.putString(FAVORITE_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (books != null) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_BOOK);
                        editor.putString(ALREADY_READ_BOOK, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }

        }
        return false;
    }

    public boolean removeFromWantToRead(Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if (books != null) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_BOOKS);
                        editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }

        }
        return false;
    }

    public boolean removeFromCurrentlyReading(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (books != null) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS);
                        editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }

        }
        return false;
    }

    public boolean removeFromFavoriteBooks(Book book) {
        ArrayList<Book> books = getFavoriteBooks();
        if (books != null) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVORITE_BOOKS);
                        editor.putString(FAVORITE_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }

        }
        return false;
    }

}
