package dev.gabrieltoledo.picpaychallenge.domain.user;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "users")
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    public User(String firstName, String lastName, String document, String email, String password, UserType userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.document = document;
        this.email = email;
        this.password = password;
        this.balance = BigDecimal.ZERO;
        this.userType = userType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 200)
    private String lastName;

    @Column(unique = true, length = 25)
    private String document;
    
    @Column(unique = true, length = 100)
    private String email;

    @Column(length = 255)
    private String password;

    @Column(precision = 19, scale = 2)
    private BigDecimal balance;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
}
