package library.controller;

import library.dto.UserDTO;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserControllerIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void findAll() {
        ResponseEntity<UserDTO[]> responseEntity = restTemplate.withBasicAuth("bpaskali", "password")
                .getForEntity("/users", UserDTO[].class);

        UserDTO[] userDTOS = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void findAllWithoutPermission() {
        ResponseEntity<LinkedHashMap> responseEntity = restTemplate.withBasicAuth("mbrkljac", "password")
                .getForEntity("/users", LinkedHashMap.class);

        LinkedHashMap linkedHashMap = responseEntity.getBody();

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void findById() {
        ResponseEntity<UserDTO> responseEntity = restTemplate.withBasicAuth("bpaskali", "password")
                .getForEntity("/users/2", UserDTO.class);

        UserDTO userDTO = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
