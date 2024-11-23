package com.ssafy.petandpeople.application.dto.ai;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserPreferenceDto {

    private String petOwnershipExperience;

    private String residenceLocation;

    private String allergy;

    private String petAllowedHome;

    private String walkPreference;

    private String yardSpace;

    private String largePetsPreference;

    private String petAgePreference;

    public UserPreferenceDto() {
    }

    public String getPetOwnershipExperience() {
        return petOwnershipExperience;
    }

    public String getResidenceLocation() {
        return residenceLocation;
    }

    public String getAllergy() {
        return allergy;
    }

    public String getPetAllowedHome() {
        return petAllowedHome;
    }

    public String getWalkPreference() {
        return walkPreference;
    }

    public String getYardSpace() {
        return yardSpace;
    }

    public String getLargePetsPreference() {
        return largePetsPreference;
    }

    public String getPetAgePreference() {
        return petAgePreference;
    }

    public String toFormattedString() {
        return "User Preferences: \n" +
                "- Pet Ownership Experience: " + (petOwnershipExperience != null ? petOwnershipExperience : "Not Provided") +
                " (Has the user owned a pet before? Yes/No)\n" +
                "- Residence Location: " + (residenceLocation != null ? residenceLocation : "Not Provided") +
                " (The city or area where the user resides)\n" +
                "- Allergy: " + (allergy != null ? allergy : "Not Provided") +
                " (Does the user have any allergies related to animals? Yes/No)\n" +
                "- Pet Allowed in Home: " + (petAllowedHome != null ? petAllowedHome : "Not Provided") +
                " (Is the user's home pet-friendly? Yes/No)\n" +
                "- Walk Preference: " + (walkPreference != null ? walkPreference : "Not Provided") +
                " (Does the user prefer walking activities? Yes/No)\n" +
                "- Yard Space: " + (yardSpace != null ? yardSpace : "Not Provided") +
                " (Does the user have access to a yard or open space? Yes/No)\n" +
                "- Large Pets Preference: " + (largePetsPreference != null ? largePetsPreference : "Not Provided") +
                " (Does the user prefer large pets? Yes/No)\n" +
                "- Pet Age Preference: " + (petAgePreference != null ? petAgePreference : "Not Provided") +
                " (Preferred age range for the pet, e.g., young, adult, senior)";
    }

}
