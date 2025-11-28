package com.Etech.census.DTO;


import java.util.List;

import lombok.Data;

@Data
public class HouseholdDTO {
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

    private List<MemberDTO> members;
}
