package hybridlibrary.service.impl;

import hybridlibrary.dto.UserDTO;
import hybridlibrary.exception.NotFoundException;
import hybridlibrary.model.User;
import hybridlibrary.repository.UserRepository;
import hybridlibrary.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ConversionService conversionService;

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(user -> conversionService.convert(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findOne(long id) {
        log.info("Finding user with id {}", id);
        return userRepository.findById(id)
                .map(user -> conversionService.convert(user, UserDTO.class))
                .orElseThrow(() -> new NotFoundException(String.format("User with id %s not found", id)));
    }

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new NotFoundException(String.format("User with username %s not found", username)));
    }

}