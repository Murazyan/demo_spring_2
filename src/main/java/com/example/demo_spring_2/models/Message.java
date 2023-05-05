package com.example.demo_spring_2.models;

import com.example.demo_spring_2.models.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    protected int id;

    @Column
    @NotBlank(message = "Text is required")
    private String text;


    @ManyToOne(/*cascade = CascadeType.ALL*/)
    @JoinColumn(name="to_user_id", nullable=false)
    private User toUser;

    @ManyToOne(/*cascade = CascadeType.ALL*/)
    @JoinColumn(name="from_user_id", nullable=false)
    private User fromUser;


    @Column
    private LocalDateTime created;

    @PrePersist
    public void onCreate() {
        this.created = LocalDateTime.now();
    }


}


