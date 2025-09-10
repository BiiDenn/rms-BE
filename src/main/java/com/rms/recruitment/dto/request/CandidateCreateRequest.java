package com.rms.recruitment.dto.request;

import com.rms.recruitment.dto.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateCreateRequest {
    @NotBlank(message = "Họ không được để trống")
    private String lastName;

    @NotBlank(message = "Tên không được để trống")
    private String firstName;

    @NotNull(message = "Ngày sinh không được để trống")
    private LocalDate dateOfBirth;

    private String gender;

    @Email(message = "Email chính không đúng định dạng")
    private String mainEmail;

    @Email(message = "Email phụ không đúng định dạng")
    private String subEmail;

    private String mainPhone;
    private String subPhone;
    private String nationality;
    private String primaryLanguage;
    private String secondaryLanguage;
    private String recruitmentSource;
    private String referrer;
    private String maritalStatus;
    private AddressDTO address;
    private String qualifications;
    private Integer recruitmentId;

    public @NotBlank(message = "Họ không được để trống") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "Họ không được để trống") String lastName) {
        this.lastName = lastName;
    }

    public @NotBlank(message = "Tên không được để trống") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "Tên không được để trống") String firstName) {
        this.firstName = firstName;
    }

    public @NotNull(message = "Ngày sinh không được để trống") LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@NotNull(message = "Ngày sinh không được để trống") LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public @Email(message = "Email chính không đúng định dạng") String getMainEmail() {
        return mainEmail;
    }

    public void setMainEmail(@Email(message = "Email chính không đúng định dạng") String mainEmail) {
        this.mainEmail = mainEmail;
    }

    public @Email(message = "Email phụ không đúng định dạng") String getSubEmail() {
        return subEmail;
    }

    public void setSubEmail(@Email(message = "Email phụ không đúng định dạng") String subEmail) {
        this.subEmail = subEmail;
    }

    public String getMainPhone() {
        return mainPhone;
    }

    public void setMainPhone(String mainPhone) {
        this.mainPhone = mainPhone;
    }

    public String getSubPhone() {
        return subPhone;
    }

    public void setSubPhone(String subPhone) {
        this.subPhone = subPhone;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPrimaryLanguage() {
        return primaryLanguage;
    }

    public void setPrimaryLanguage(String primaryLanguage) {
        this.primaryLanguage = primaryLanguage;
    }

    public String getSecondaryLanguage() {
        return secondaryLanguage;
    }

    public void setSecondaryLanguage(String secondaryLanguage) {
        this.secondaryLanguage = secondaryLanguage;
    }

    public String getRecruitmentSource() {
        return recruitmentSource;
    }

    public void setRecruitmentSource(String recruitmentSource) {
        this.recruitmentSource = recruitmentSource;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public Integer getRecruitmentId() {
        return recruitmentId;
    }

    public void setRecruitmentId(Integer recruitmentId) {
        this.recruitmentId = recruitmentId;
    }
}
