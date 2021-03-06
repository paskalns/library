package library.controller;

import library.dto.BookDTO;
import library.service.BookService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookControllerIntegrationTest extends AbstractIntegrationTest {

    private BookService bookService;

    @Test
    public void findAll() {
        ResponseEntity<BookDTO[]> responseEntity = restTemplate.withBasicAuth("bpaskali", "password")
                .getForEntity("/books", BookDTO[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void findOne() {
        ResponseEntity<BookDTO> responseEntity = restTemplate.withBasicAuth("bpaskali", "password")
                .getForEntity("/books/1", BookDTO.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void createBook() {
        int sizeBefore = bookService.findAll().size();

        ResponseEntity<BookDTO> responseEntity = restTemplate.withBasicAuth("bpaskali", "password")
                .postForEntity("/books",
                        new BookDTO(null, 333, "name", "author", 20, 20)
                , BookDTO.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(sizeBefore + 1, bookService.findAll().size());
    }

    @Test
    public void deleteBook() {
        BookDTO bookDTO = bookService.save
                (new BookDTO(null, 444, "name", "author", 20, 20));
        int size = bookService.findAll().size();

        ResponseEntity<Void> responseEntity = restTemplate.withBasicAuth("bpaskali", "password")
                .exchange("/books/" + bookDTO.getId(), HttpMethod.DELETE, new HttpEntity<Object>(null), Void.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(size - 1, bookService.findAll().size());
    }

    @Test
    public void updateBook() {
        ResponseEntity<BookDTO> responseEntity = restTemplate.withBasicAuth("bpaskali", "password").
                exchange("/books/1", HttpMethod.PUT, new HttpEntity<>
                        (new BookDTO(1L, 999, "name1", "author1", 30, 30 ))
                        , BookDTO.class);

        BookDTO bookDTO = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(bookDTO);
        assertEquals("author1", bookDTO.getAuthors());
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

}
