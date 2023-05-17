package com.example.common.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

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


