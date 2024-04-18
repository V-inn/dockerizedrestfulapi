package com.viniciusfk.dockerizedrestfulapi.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Name must have atleast 3 digits.")
    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Email(message = "Invalid email.")
    @NotBlank(message = "Email is required.")
    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @NotBlank(message = "Please insert a password.")
    @Column(name = "password", columnDefinition = "TEXT", nullable = false)
    private String password;

    @NotBlank(message = "Phonte number is required.")
    @Column(name = "phone", length = 15, nullable = false)
    private String phone;
}