package library.service;

import library.dto.UserDTO;
import library.model.User;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findOne(long id);

    User loadUserByUsername(String username);

}
