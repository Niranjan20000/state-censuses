package com.Etech.census.entity;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Entity
@Table(name = "households")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Household {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String surveyTimestamp;
    private String householdId;
    private String headName;
    private Integer headAge;
    private String aadhar;
    private String address;
    private String state;
    private String district;
    private String subDistrict;
    private String village;
    private String ward;
    private String pinCode;
    private String houseNumber;
    private String street;
    private String houseType;
    private String ownershipStatus;
    private Integer numberOfRooms;
    private String toiletFacility;
    private String drinkingWaterSource;
    private String electricityAvailable;
    private String kitchenAvailable;
    private String cookingFuel;
    private String hasRefrigerator;
    private String hasTV;
    private String internetConnection;
    private String vehicleOwnership;

    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL, orphanRemoval = true)
    @Singular
    private final List<Member> members = new ArrayList<>();
}
