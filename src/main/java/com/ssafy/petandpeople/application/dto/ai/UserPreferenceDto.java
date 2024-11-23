package com.ssafy.petandpeople.application.dto.ai;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserPreferenceDto {

    private String petOwnershipExperience;

    private String residenceLocation;

    private String allergy;

    private String petAllowedHome;

    private String walkPreference;

    private String yardSpace;

    private String petSizePreference;

    private String petAgePreference;

    private String petSexPreference;

    private String petSpeciesPreference;

    private String petBreedPreference;

    private String neuteringPreference;

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

    public String getPetSizePreference() {
        return petSizePreference;
    }

    public String getPetAgePreference() {
        return petAgePreference;
    }

    public String getPetSexPreference() {
        return petSexPreference;
    }

    public String getPetSpeciesPreference() {
        return petSpeciesPreference;
    }

    public String getBreedPreference() {
        return petBreedPreference;
    }

    public String getNeuteringPreference() {
        return neuteringPreference;
    }

    public String toFormattedStringForPrompting() {
        return Stream.of(
                "- Pet Ownership Experience: " + Optional.ofNullable(petOwnershipExperience).orElse("Not Provided") +
                        " (Has the user owned a pet before? Yes/No)",
                "- Residence Location: " + Optional.ofNullable(residenceLocation).orElse("Not Provided") +
                        " (The city or area where the user resides)",
                "- Allergy: " + Optional.ofNullable(allergy).orElse("Not Provided") +
                        " (Does the user have any allergies related to animals? Yes/No)",
                "- Pet Allowed in Home: " + Optional.ofNullable(petAllowedHome).orElse("Not Provided") +
                        " (Is the user able to raise pet in home? Yes/No)",
                "- Walk Preference: " + Optional.ofNullable(walkPreference).orElse("Not Provided") +
                        " (Does the user prefer walking activities? Yes/No)",
                "- Yard Space: " + Optional.ofNullable(yardSpace).orElse("Not Provided") +
                        " (Does the user have access to a yard or open space? Yes/No)",
                "- Pet Size Preference: " + Optional.ofNullable(petSizePreference).orElse("Not Provided") +
                        " (Does the user prefer large pet or Small pet? large/small)",
                "- Pet Age Preference: " + Optional.ofNullable(petAgePreference).orElse("Not Provided") +
                        " (Preferred age range for the pet, e.g., young, adult, senior)",
                "- Pet Sex Preference: " + Optional.ofNullable(petSexPreference).orElse("Not Provided") +
                        " (Does the user have a preference for the pet's sex? Male/Female/No Preference)",
                "- Pet Species Preference: " + Optional.ofNullable(petSpeciesPreference).orElse("Not Provided") +
                        " (Does the user have a preference for the pet's species? Dog/Cat/Other/No Preference)",
                "- Pet Breed Preference: " + Optional.ofNullable(petBreedPreference).orElse("Not Provided") +
                        " (Does the user have a preference for the pet's breed? Specify breed or No Preference)",
                "- Neutering Preference: " + Optional.ofNullable(neuteringPreference).orElse("Not Provided") +
                        " (Does the user prefer neutered pets? Yes/No/No Preference)"
        ).collect(Collectors.joining("\n"));
    }

}
