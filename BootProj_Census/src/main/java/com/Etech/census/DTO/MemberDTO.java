package com.Etech.census.DTO;

import lombok.Data;

@Data
public class MemberDTO {
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
}
