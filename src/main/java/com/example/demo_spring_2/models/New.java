package com.example.demo_spring_2.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;


import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "news")
public class New {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    private int id;

    @Column
    private String title;

    @Column
    private String content;

    @ElementCollection
    private List<String>  resources;

    @ManyToOne(/*cascade = CascadeType.ALL*/)
    @JoinColumn(name="group_id", nullable=false)
    private Group group;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;



}
