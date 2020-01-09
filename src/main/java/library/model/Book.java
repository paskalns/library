package library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private int serialNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String authors;

    @Column(nullable = false)
    private int bookQty;

    @Column(nullable = false)
    private int bookQtyCopy;

}
