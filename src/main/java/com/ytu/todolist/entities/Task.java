package com.ytu.todolist.entities;

import com.ytu.todolist.entities.enums.MissionStatus;
import com.ytu.todolist.entities.enums.Priority;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

// todo : Task -> Id, CategoryName, Title, Description, startDate, endDate, createDate, priority, missionStatus
// missionStatus = IN_PROCESS , DONE

// Enum

// priority -> düşük öncelikli , Mormal , Çok Önemli
@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;


    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "category_id")
    private Category category;



    @Column(name = "title")
    private String  title;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;


    @Column(name = "mission_status")
    @Enumerated(EnumType.STRING)
    private MissionStatus missionStatus;
}
