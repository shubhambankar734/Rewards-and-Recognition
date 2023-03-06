package com.rewards.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="`user`")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String passward;
    private Long accountId;
    private String email;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="ManagerId" , referencedColumnName = "id")
    private User manager;
}
