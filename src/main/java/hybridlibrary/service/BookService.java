package hybridlibrary.service;

import hybridlibrary.dto.BookDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BookService {

    BookDTO save(BookDTO bookDTO);

    List<BookDTO> findAll();

    BookDTO findOne(long id);

    void delete(Long id);

    BookDTO update(Long id, @RequestBody BookDTO bookDTO);

}
