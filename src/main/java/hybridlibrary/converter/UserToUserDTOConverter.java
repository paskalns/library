package hybridlibrary.converter;

import hybridlibrary.dto.UserDTO;
import hybridlibrary.model.User;
import org.springframework.core.convert.converter.Converter;

public class UserToUserDTOConverter implements Converter<User, UserDTO> {

    public UserDTO convert(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }

}
