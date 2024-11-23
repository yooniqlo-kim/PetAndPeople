package com.ssafy.petandpeople.application.converter.adoption;

import com.ssafy.petandpeople.application.dto.abandonedanimal.AbandonedAnimalDto;
import com.ssafy.petandpeople.application.dto.ai.FilteredAbandonedAnimalDto;

import java.util.List;
import java.util.stream.Collectors;

public class AdoptionConverter {

    public static List<FilteredAbandonedAnimalDto> toFilteredDataCluster(List<AbandonedAnimalDto> adoptionDataCluster) {
        return adoptionDataCluster.stream()
                .map(adoptionData -> new FilteredAbandonedAnimalDto(
                        adoptionData.getAbdmIdntfyNo(),
                        adoptionData.getSpeciesNm(),
                        adoptionData.getColorNm(),
                        adoptionData.getAgeInfo(),
                        adoptionData.getBdwghInfo(),
                        adoptionData.getSexNm(),
                        adoptionData.getNeutYn(),
                        adoptionData.getSfetrInfo(),
                        adoptionData.getShterNm(),
                        adoptionData.getShterTelno(),
                        adoptionData.getProtectPlc(),
                        adoptionData.getThumbImageCours(),
                        adoptionData.getPartclrMatr()
                ))
                .collect(Collectors.toList());
    }

}
