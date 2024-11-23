package com.ssafy.petandpeople.application.converter.adoption;

import com.ssafy.petandpeople.application.dto.adoption.AdoptionDto;
import com.ssafy.petandpeople.application.dto.adoption.AdoptionSummaryDto;

import java.util.List;
import java.util.stream.Collectors;

public class AdoptionConverter {

    public static List<AdoptionSummaryDto> toFilteredData(List<AdoptionDto> adoptionData) {
        return adoptionData.stream()
                .map(dto -> new AdoptionSummaryDto(
                        dto.getAbdmIdntfyNo(),
                        dto.getSpeciesNm(),
                        dto.getColorNm(),
                        dto.getAgeInfo(),
                        dto.getBdwghInfo(),
                        dto.getSexNm(),
                        dto.getNeutYn(),
                        dto.getSfetrInfo(),
                        dto.getShterNm(),
                        dto.getShterTelno(),
                        dto.getProtectPlc(),
                        dto.getThumbImageCours(),
                        dto.getPartclrMatr()
                ))
                .collect(Collectors.toList());
    }

}
