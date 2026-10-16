package org.example.librarymanagement.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String fullName;

    @Enumerated(EnumType.STRING)
    private Role role;

    public void changeRole(Role newRole) {
        this.role = newRole;
    }
}
