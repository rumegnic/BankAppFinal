package ro.bank.entities.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {

    @Id
    @Column(length = 50, nullable = false)
    String username;

    @Column(length = 500, nullable = false)
    String password;

    @Column(nullable = false)
    Boolean enabled;


}
