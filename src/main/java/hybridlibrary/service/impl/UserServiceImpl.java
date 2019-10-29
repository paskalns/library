package hybridlibrary.service.impl;

import hybridlibrary.dto.UserDTO;
import hybridlibrary.model.User;
import hybridlibrary.repository.UserRepository;
import hybridlibrary.service.UserService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ConversionService conversionService;

    public UserServiceImpl(UserRepository userRepository, ConversionService conversionService) {
        this.userRepository = userRepository;
        this.conversionService = conversionService;
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(user -> conversionService.convert(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO findOne(long id) {
        Optional<UserDTO> user = userRepository.findById(id)
                .map(user1 -> conversionService.convert(user1, UserDTO.class));
        return user.orElse(null);


    }

}
