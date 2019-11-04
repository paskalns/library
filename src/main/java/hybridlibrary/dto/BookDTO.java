package hybridlibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Long id;

    private int serialNumber;

    private String name;

    private String authors;

    private int bookQty;

    private int bookQtyCopy;

}
