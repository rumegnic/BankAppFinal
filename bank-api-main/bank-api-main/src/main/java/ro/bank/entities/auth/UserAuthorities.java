package ro.bank.entities.auth;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "authorities", uniqueConstraints = {@UniqueConstraint(name = "ix_auth_username", columnNames = {"username", "authority"})})
public class UserAuthorities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    Long id;

    @Column(length = 50, nullable = false)
    @JoinColumn(name = "fk_authorities_users", referencedColumnName = "username", table = "users")
    String username;

    @Column(length = 50, nullable = false)
    String authority;

}
