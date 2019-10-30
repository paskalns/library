package hybridlibrary.service.impl;

import hybridlibrary.dto.UserDTO;
import hybridlibrary.exception.NotFoundException;
import hybridlibrary.model.User;
import hybridlibrary.repository.UserRepository;
import hybridlibrary.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ConversionService conversionService;

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(user -> conversionService.convert(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO findOne(long id) {
        return userRepository.findById(id)
                .map(user -> conversionService.convert(user, UserDTO.class))
                .orElseThrow(() -> new NotFoundException(String.format("User with id %s not found", id)));
    }

}
