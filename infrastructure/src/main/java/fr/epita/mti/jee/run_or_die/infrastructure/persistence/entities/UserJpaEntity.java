package fr.epita.mti.jee.run_or_die.infrastructure.persistence.entities;

import fr.epita.mti.jee.run_or_die.domain.models.utilisateur.Utilisateur;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserJpaEntity {
    @Id
    String username;
    String  password;
    String  role;
    boolean licensed;

    public UserJpaEntity() {
    }

    public UserJpaEntity(String username, String password, String role, boolean licensed) {
        this.username = username;
        this.password = password;
        this.role     = role;
        this.licensed = licensed;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public Utilisateur toDomain() {
        return new Utilisateur(username, password, licensed);
    }
}
