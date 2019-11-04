package hybridlibrary.repository;

import hybridlibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findBySerialNumber(int serialNumber);
}
