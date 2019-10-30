package hybridlibrary.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class User extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String role;

}
