package library.service.impl;

import library.dto.BookDTO;
import library.exception.BadRequestException;
import library.exception.NotFoundException;
import library.model.Book;
import library.repository.BookRepository;
import library.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ConversionService conversionService;

    @Override
    public BookDTO findOne(long id) {
        return bookRepository.findById(id)
                .map(book -> conversionService.convert(book, BookDTO.class))
                .orElseThrow(() -> new NotFoundException(String.format("Book with id %s not found", id)));
    }

    @Override
    public BookDTO save(BookDTO bookDTO) {
        if (isPresentBookWithSerialNumber(bookDTO.getSerialNumber()).isPresent()) {
            throw new BadRequestException("Book with that serial number already exists.");
        }
        Book book = conversionService.convert(bookDTO, Book.class);
        return conversionService.convert(bookRepository.save(book), BookDTO.class);
    }

    @Override
    public List<BookDTO> findAll() {
        return bookRepository.findAll().stream()
                .map(book -> conversionService.convert(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book with id %s not found", id)));
        bookRepository.delete(book);
    }

    @Override
    public BookDTO update(Long id, BookDTO bookDTO) {
        bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book with id %s not found", id)));
        Book bookBySerialNumber = bookRepository.findBySerialNumber(bookDTO.getSerialNumber());
        if (bookBySerialNumber != null && bookBySerialNumber.getId() != id) {
            throw new BadRequestException("Book with that serial number already exists.");
        }

        Book book = conversionService.convert(bookDTO, Book.class);
        book.setId(id);
        return conversionService.convert(bookRepository.save(book), BookDTO.class);
    }

    private Optional<Book> isPresentBookWithSerialNumber(int serialNumber) {
        return Optional.ofNullable(bookRepository.findBySerialNumber(serialNumber));
    }

}
