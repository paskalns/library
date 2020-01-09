package hybridlibrary.converter;

import hybridlibrary.dto.UserDTO;
import hybridlibrary.model.User;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserToUserDTOConverterTest {

    private UserToUserDTOConverter converter = new UserToUserDTOConverter();

    @Test
    public void testConverter() {
        String firstName = "Branko";
        String lastName = "Paskali";

        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();

        UserDTO userDTO = converter.convert(user);
        assertNotNull(userDTO);
        assertEquals(firstName, userDTO.getFirstName());
        assertEquals(lastName, userDTO.getLastName());
    }

}
