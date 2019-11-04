package hybridlibrary.converter;

import hybridlibrary.dto.BookDTO;
import hybridlibrary.model.Book;
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
