package hybridlibrary.service;

import hybridlibrary.dto.UserDTO;
import hybridlibrary.model.User;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findOne(long id);

    User loadUserByUsername(String username);

}
