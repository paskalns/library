package hybridlibrary.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String role;

}
