package hybridlibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Long id;

    @Min(1)
    private int serialNumber;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Authors cannot be blank")
    private String authors;

    @Min(1)
    private int bookQty;

    @Min(1)
    private int bookQtyCopy;

}
