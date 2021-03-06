package library.converter;

import library.dto.BookDTO;
import library.model.Book;
import org.springframework.core.convert.converter.Converter;

public class BookToBookDTOConverter implements Converter<Book, BookDTO> {

    public BookDTO convert(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .serialNumber(book.getSerialNumber())
                .name(book.getName())
                .authors(book.getAuthors())
                .bookQty(book.getBookQty())
                .bookQtyCopy(book.getBookQtyCopy())
                .build();
    }

}
