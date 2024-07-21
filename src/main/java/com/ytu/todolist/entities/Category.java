package com.ytu.todolist.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;


    @Column(name = "category_name")
    private String name;


    @Column(name = "category_description")
    private String description;


    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Task> tasks;

}
