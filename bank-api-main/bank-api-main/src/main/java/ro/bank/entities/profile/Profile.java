package ro.bank.entities.profile;

import lombok.*;
import ro.bank.entities.Audit;
import ro.bank.entities.auth.User;
import ro.bank.entities.values.FullName;
import ro.bank.entities.values.IdentificationNumber;
import ro.bank.entities.values.Location;
import ro.bank.entities.values.PhoneNumber;

import javax.persistence.*;

@Entity
@Table(name = "profiles")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Profile extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    User user;

    @Embedded
    FullName fullName;
    @Embedded
    Location location;
    @Embedded
    PhoneNumber phoneNumber;
    @Embedded
    IdentificationNumber identificationNumber;
}
