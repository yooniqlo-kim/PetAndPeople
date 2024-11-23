package com.ssafy.petandpeople.application.dto.adoption;

public class AdoptionSummaryDto {

    private String abdmIdntfyNo;
    private String speciesNm;
    private String colorNm;
    private String ageInfo;
    private String bdwghInfo;
    private String sexNm;
    private String neutYn;
    private String sfetrInfo;
    private String shterNm;
    private String shterTelno; // Added field
    private String protectPlc;
    private String thumbImageCours;
    private String partclrMatr;

    public AdoptionSummaryDto() {
    }

    public AdoptionSummaryDto(String abdmIdntfyNo, String speciesNm, String colorNm, String ageInfo, String bdwghInfo, String sexNm, String neutYn, String sfetrInfo, String shterNm, String shterTelno, String protectPlc, String thumbImageCours, String partclrMatr) {
        this.abdmIdntfyNo = abdmIdntfyNo;
        this.speciesNm = speciesNm;
        this.colorNm = colorNm;
        this.ageInfo = ageInfo;
        this.bdwghInfo = bdwghInfo;
        this.sexNm = sexNm;
        this.neutYn = neutYn;
        this.sfetrInfo = sfetrInfo;
        this.shterNm = shterNm;
        this.shterTelno = shterTelno;
        this.protectPlc = protectPlc;
        this.thumbImageCours = thumbImageCours;
        this.partclrMatr = partclrMatr;
    }

    public String getAbdmIdntfyNo() {
        return abdmIdntfyNo;
    }

    public String getSpeciesNm() {
        return speciesNm;
    }

    public String getColorNm() {
        return colorNm;
    }

    public String getAgeInfo() {
        return ageInfo;
    }

    public String getBdwghInfo() {
        return bdwghInfo;
    }

    public String getSexNm() {
        return sexNm;
    }

    public String getNeutYn() {
        return neutYn;
    }

    public String getSfetrInfo() {
        return sfetrInfo;
    }

    public String getShterNm() {
        return shterNm;
    }

    public String getShterTelno() {
        return shterTelno; // Getter for the new field
    }

    public String getProtectPlc() {
        return protectPlc;
    }

    public String getThumbImageCours() {
        return thumbImageCours;
    }

    public String getPartclrMatr() {
        return partclrMatr;
    }

    @Override
    public String toString() {
        return "AdoptionSummaryDto{" +
                "Identification Number='" + abdmIdntfyNo + '\'' +
                ", Species='" + speciesNm + '\'' +
                ", Color='" + colorNm + '\'' +
                ", Age='" + ageInfo + '\'' +
                ", Weight='" + bdwghInfo + '\'' +
                ", Gender='" + sexNm + '\'' +
                ", Neutered='" + neutYn + '\'' +
                ", Features='" + sfetrInfo + '\'' +
                ", Shelter Name='" + shterNm + '\'' +
                ", Shelter Phone='" + shterTelno + '\'' + // Added field in toString
                ", Shelter Location='" + protectPlc + '\'' +
                ", Thumbnail Image='" + thumbImageCours + '\'' +
                ", Special Notes='" + partclrMatr + '\'' +
                '}';
    }

}
