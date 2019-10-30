package hybridlibrary.service;

import hybridlibrary.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findOne(long id);

}
