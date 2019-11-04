package hybridlibrary.service.impl;

import hybridlibrary.dto.BookDTO;
import hybridlibrary.exception.BadRequestException;
import hybridlibrary.exception.NotFoundException;
import hybridlibrary.model.Book;
import hybridlibrary.repository.BookRepository;
import hybridlibrary.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ConversionService conversionService;

    public boolean isPresentBookWithSerialNumber(int serialNumber) {
        if ((bookRepository.findBySerialNumber(serialNumber)) == null) {
            return false;
        } else {
            return true;
        }
    }

    public BookDTO findOne(long id) {
        return bookRepository.findById(id)
                .map(book -> conversionService.convert(book, BookDTO.class))
                .orElseThrow(() -> new NotFoundException(String.format("Book with id %s not found", id)));
    }

    public BookDTO save(BookDTO bookDTO) {
        if (isPresentBookWithSerialNumber(bookDTO.getSerialNumber())) {
            throw new BadRequestException("Book with that serial number allready exists.");
        }
        Book book = new Book();
        book.setSerialNumber(bookDTO.getSerialNumber());
        book.setName(bookDTO.getName());
        book.setAuthors(bookDTO.getAuthors());
        book.setBookQty(bookDTO.getBookQty());
        book.setBookQtyCopy(book.getBookQtyCopy());
        return conversionService.convert(bookRepository.save(book), BookDTO.class);
    }

    public List<BookDTO> findAll() {
        return bookRepository.findAll().stream()
                .map(book -> conversionService.convert(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book with id %s not found", id)));
        bookRepository.delete(book);
    }

    public BookDTO update(Long id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book with id %s not found", id)));
        Book bookBySerialNumber = bookRepository.findBySerialNumber(bookDTO.getSerialNumber());
        if (bookBySerialNumber != null && bookBySerialNumber.getId() != id) {
            throw new BadRequestException("Book with that serial number allready exists.");
        }
        book.setSerialNumber(bookDTO.getSerialNumber());
        book.setName(bookDTO.getName());
        book.setAuthors(bookDTO.getAuthors());
        book.setBookQty(bookDTO.getBookQty());
        book.setBookQtyCopy(bookDTO.getBookQtyCopy());
        return conversionService.convert(bookRepository.save(book), BookDTO.class);
    }

}
