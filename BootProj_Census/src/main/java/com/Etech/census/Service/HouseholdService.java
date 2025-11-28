package com.Etech.census.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Etech.census.DTO.HouseholdDTO;
import com.Etech.census.DTO.MemberDTO;
import com.Etech.census.Respository.HouseholdRepository;
import com.Etech.census.entity.Household;
import com.Etech.census.entity.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HouseholdService {

    private final HouseholdRepository householdRepository;

    @Transactional
    public Household saveFromDto(HouseholdDTO dto) {
        // Build the Household entity from the DTO
        Household h = Household.builder()
            .surveyTimestamp(dto.getSurveyTimestamp())
            .householdId(dto.getHouseholdId())
            .headName(dto.getHeadName())
            .headAge(dto.getHeadAge())
            .aadhar(dto.getAadhar())
            .address(dto.getAddress())
            .state(dto.getState())
            .district(dto.getDistrict())
            .subDistrict(dto.getSubDistrict())
            .village(dto.getVillage())
            .ward(dto.getWard())
            .pinCode(dto.getPinCode())
            .houseNumber(dto.getHouseNumber())
            .street(dto.getStreet())
            .houseType(dto.getHouseType())
            .ownershipStatus(dto.getOwnershipStatus())
            .numberOfRooms(dto.getNumberOfRooms())
            .toiletFacility(dto.getToiletFacility())
            .drinkingWaterSource(dto.getDrinkingWaterSource())
            .electricityAvailable(dto.getElectricityAvailable())
            .kitchenAvailable(dto.getKitchenAvailable())
            .cookingFuel(dto.getCookingFuel())
            .hasRefrigerator(dto.getHasRefrigerator())
            .hasTV(dto.getHasTV())
            .internetConnection(dto.getInternetConnection())
            .vehicleOwnership(dto.getVehicleOwnership())
            .build();

        // Add members if present
        if (dto.getMembers() != null) {
            for (MemberDTO md : dto.getMembers()) {
                if (md == null || md.getFullName() == null || md.getFullName().trim().isEmpty()) continue;
                Member m = Member.builder()
                    .fullName(md.getFullName())
                    .aadhar(md.getAadhar())
                    .relationship(md.getRelationship())
                    .gender(md.getGender())
                    .age(md.getAge())
                    .disability(md.getDisability())
                    .maritalStatus(md.getMaritalStatus())
                    .motherTongue(md.getMotherTongue())
                    .educationLevel(md.getEducationLevel())
                    .employmentStatus(md.getEmploymentStatus())
                    .household(h) // link member to household
                    .build();
                h.getMembers().add(m);
            }
        }

        // ✅ Now save the household
        return householdRepository.save(h);
    }

    // ---------------- Survey Report Methods ----------------

    public long countHouseholds() {
        return householdRepository.count();
    }

    public long countMembers() {
        return householdRepository.findAll().stream()
                .mapToLong(h -> h.getMembers().size())
                .sum();
    }

    public long countTodaysSurveys() {
        String today = LocalDate.now().toString(); // yyyy-MM-dd
        return householdRepository.findAll().stream()
                .filter(h -> h.getSurveyTimestamp() != null && h.getSurveyTimestamp().startsWith(today))
                .count();
    }

    public long countInternetConnected() {
        return householdRepository.findAll().stream()
                .filter(h -> "Yes".equalsIgnoreCase(h.getInternetConnection()))
                .count();
    }

    public long countElectricityAvailable() {
        return householdRepository.findAll().stream()
                .filter(h -> "Yes".equalsIgnoreCase(h.getElectricityAvailable()))
                .count();
    }

    public long countTvAvailable() {
        return householdRepository.findAll().stream()
                .filter(h -> "Yes".equalsIgnoreCase(h.getHasTV()))
                .count();
    }

    public long countRefrigeratorAvailable() {
        return householdRepository.findAll().stream()
                .filter(h -> "Yes".equalsIgnoreCase(h.getHasRefrigerator()))
                .count();
    }

    public List<String> getLast7DaysLabels() {
        return IntStream.rangeClosed(0, 6)
                .mapToObj(i -> LocalDate.now().minusDays(6 - i).toString())
                .collect(Collectors.toList());
    }

    public List<Long> getLast7DaysCounts() {
        return getLast7DaysLabels().stream()
                .map(date -> householdRepository.findAll().stream()
                        .filter(h -> h.getSurveyTimestamp() != null && h.getSurveyTimestamp().startsWith(date))
                        .count())
                .collect(Collectors.toList());
    }

    public List<Map<String, String>> getHouseholdsByDate(String date) {
        DateTimeFormatter isoFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter altFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter usFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy, hh:mm:ss a");

        LocalDate inputDate = LocalDate.parse(date, isoFormatter);

        return householdRepository.findAll().stream()
            .filter(h -> {
                if (h.getSurveyTimestamp() == null) return false;
                String ts = h.getSurveyTimestamp();

                try {
                    LocalDate surveyDate;

                    if (ts.length() >= 10 && ts.charAt(4) == '-') {
                        // yyyy-MM-dd or yyyy-MM-ddTHH:mm:ss
                        surveyDate = LocalDate.parse(ts.substring(0, 10), isoFormatter);
                    } else if (ts.contains("-")) {
                        // dd-MM-yyyy
                        surveyDate = LocalDate.parse(ts, altFormatter);
                    } else if (ts.contains("/")) {
                        // MM/dd/yyyy, hh:mm:ss a
                        surveyDate = LocalDate.parse(ts.split(",")[0], DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                    } else {
                        return false;
                    }

                    return surveyDate.equals(inputDate);
                } catch (Exception e) {
                    System.out.println("Could not parse timestamp: " + ts);
                    return false;
                }
            })
            .map(h -> Map.of("id", h.getHouseholdId(), "head", h.getHeadName()))
            .collect(Collectors.toList());
    }
    
 // ✅ Added wrapper for total households
    public long countAll() {
        return countHouseholds();
    }

    // ✅ Added wrapper for today's registrations
    public long countToday() {
        return countTodaysSurveys();
    }

    // ✅ Added method for recent households
    public List<Household> findRecent(int limit) {
        return householdRepository.findAll().stream()
                .sorted((h1, h2) -> Long.compare(h2.getId(), h1.getId()))
                .limit(limit)
                .collect(Collectors.toList());
    }
}