package library.converter;

import library.dto.BookDTO;
import library.model.Book;
import org.springframework.core.convert.converter.Converter;

public class BookDTOToBookConverter implements Converter<BookDTO, Book> {

    public Book convert(BookDTO bookDTO) {
        return Book.builder()
                .serialNumber(bookDTO.getSerialNumber())
                .name(bookDTO.getName())
                .authors(bookDTO.getAuthors())
                .bookQty(bookDTO.getBookQty())
                .bookQtyCopy(bookDTO.getBookQtyCopy())
                .build();
    }

}
