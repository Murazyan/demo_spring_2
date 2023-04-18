package com.example.demo_spring_2.models;

import com.example.demo_spring_2.models.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    protected int id;

    @Column
    @NotBlank(message = "User name is required")
    private String name;

    @Column
    @NotBlank(message = "User surname is required")
    private String surname;

    @Email
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "verification_code")
    private String verificationCode;

    @Column
    @NotBlank(message = "User password is required")
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private boolean locked;

    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime updated;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private List<New> news;


    @PrePersist
    public void onCreate() {
        this.locked = false;
        this.created = LocalDateTime.now();
        this.updated = created;
    }

    @PreUpdate
    public void onUpdate() {
        this.updated = LocalDateTime.now();
    }


    public boolean isVerified(){
        return verificationCode==null;
    }

}


