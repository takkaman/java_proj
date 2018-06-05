/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nyp.library;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author 160020C
 */
public class Book implements Serializable{
    
    public static final long serialVersionUID = -1L;
    private String isbn;
    private String title;
    private String author;
    private Date year;
    private String publisher;
    private String about;
    
    public Book()
    {
    }
    public String getIsbn()
    {
        return isbn;
    }
    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getAuthor()
    {
        return author;
    }
    public void setAuthor(String author)
    {
        this.author = author;
    }
    public Date getYear()
    {
        return year;
    }
    public void setYear(Date year)
    {
        this.year = year;
    }
    public String getPublisher()
    {
        return publisher;
    }
    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }
    public String getAbout()
    {
        return about;
    }
    public void setAbout(String about)
    {
        this.about = about;
    }
    
}
