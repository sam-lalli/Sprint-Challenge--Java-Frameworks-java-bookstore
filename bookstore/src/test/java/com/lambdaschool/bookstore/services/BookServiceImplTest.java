package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.BookstoreApplication;
import com.lambdaschool.bookstore.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookstore.models.Author;
import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.models.Section;
import com.lambdaschool.bookstore.models.Wrote;
import com.lambdaschool.bookstore.repository.SectionRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookstoreApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//**********
// Note security is handled at the controller, hence we do not need to worry about security here!
//**********
public class BookServiceImplTest
{

    @Autowired
    private BookService bookService;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private AuthorService authorService;

    @Before
    public void setUp() throws
            Exception
    {
        MockitoAnnotations.initMocks(this);

//        List<Book> myList = bookService.findAll();
//        for (Book b : myList) {
//            System.out.println(b.getBookid() + " " + b.getTitle());
//        }

//        List<Section> myList = new ArrayList<>();
//        sectionRepository.findAll().iterator().forEachRemaining(myList::add);
//        for (Section s : myList)
//        {
//            System.out.println(s.getSectionid() + " " + s.getName());
//        }

        List<Author> myList = new ArrayList<>();
        authorService.findAll().iterator().forEachRemaining(myList::add);
        for (Author a : myList)
        {
            System.out.println(a.getAuthorid() + " " + a.getFname());
        }
    }

    @After
    public void tearDown() throws
            Exception
    {
    }

    @Test
    public void findAll()
    {
        assertEquals(5, bookService.findAll().size());
    }

    @Test
    public void findBookById()
    {
        assertEquals("Digital Fortess", bookService.findBookById(27).getTitle());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void notFindBookById()
    {
        assertEquals(null, bookService.findBookById(100).getTitle());
    }

    @Test
    public void z_delete()
    {
        bookService.delete(30);
        assertEquals(4, bookService.findAll().size());
        //should return 4 since book with the id of 30 is deleted
    }

    @Test
    public void z_save()
    {
        Author a1 = new Author("John", "Mitchell");
        a1.setAuthorid(15);

        Section s1 = new Section();
        s1.setSectionid(21);
        s1.setName("Fiction");

//        Set<Wrote> wrote = new HashSet<>();
        Book b3 = new Book("Test Book", "9780307474278", 2009, s1);
        b3.getWrotes().add(new Wrote(a1, new Book()));
//        b3.setWrotes(wrote);

        Book addBook = bookService.save(b3);

        assertNotNull(addBook);
        assertEquals("Test Book", addBook.getTitle());
    }

    @Test
    public void update()
    {
    }

    @Test
    public void deleteAll()
    {
    }
}