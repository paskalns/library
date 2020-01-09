package library.service;

import library.dto.BookDTO;

import java.util.List;

public interface BookService {

    BookDTO save(BookDTO bookDTO);

    List<BookDTO> findAll();

    BookDTO findOne(long id);

    void delete(Long id);

    BookDTO update(Long id, BookDTO bookDTO);

}
