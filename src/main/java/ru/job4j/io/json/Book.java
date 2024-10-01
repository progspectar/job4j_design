package ru.job4j.io.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Book {
    private final String name;
    private final boolean isAvailable;
    private final int id;
    private final String[] genres;
    private final Author author;

    public Book(String name, boolean isAvailable, int id, Author author, String[] genres) {
        this.name = name;
        this.isAvailable = isAvailable;
        this.id = id;
        this.author = author;
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Book{"
                + "name='" + name + '\''
                + ", isAvailable=" + isAvailable
                + ", id=" + id
                + ", author=" + author
                + ", genres=" + Arrays.toString(genres)
                +
                '}';
    }

    public static void main(String[] args) {
        Book book = new Book("Java: The Complete Reference, 11th Edition",
                true, 1,
                new Author("Herbert Schildt"),
                new String[]{"Programming Languages", "Computer Science"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(book));
        final String bookJson =
                "{"
                        + "\"name\":\"The Adventures of Tom Sawyer\","
                        + "\"isAvailable\":false,"
                        + "\"id\":2,"
                        + "\"author\":"
                        + "{"
                        + "\"name\":\"Mark Twain\""
                        + "},"
                        + "\"genres\":"
                        + "[\"For kids\",\"American Fiction\"]"
                        + "}";
        final Book bookMod = gson.fromJson(bookJson, Book.class);
        System.out.println(bookMod);
    }
}
