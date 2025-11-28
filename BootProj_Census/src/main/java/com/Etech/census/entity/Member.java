package com.Etech.census.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "members")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String aadhar;
    private String relationship;
    private String gender;
    private Integer age;
    private String disability;
    private String maritalStatus;
    private String motherTongue;
    private String educationLevel;
    private String employmentStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "household_id")
    private Household household;
}
