package com.example.demo_spring_2.models;

import com.example.demo_spring_2.models.enums.UserGroupState;
import jakarta.persistence.*;
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
@Table(name = "user_groups")
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    protected int id;

    @Column
    @Enumerated(EnumType.STRING)
    private UserGroupState state;

    @ManyToOne(/*cascade = CascadeType.ALL*/)
    @JoinColumn(name="group_id", nullable=false)
    private Group group;

    @ManyToOne(/*cascade = CascadeType.ALL*/)
    @JoinColumn(name="user_id", nullable=false)
    private User user;


    @Column
    private LocalDateTime created;
    @PrePersist
    public void prePersist(){
        this.state = UserGroupState.WAITING;
        this.created  = LocalDateTime.now();
    }



}
