package com.technical.test.resource;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;


@Setter
@Getter
@NoArgsConstructor
@ToString
@Valid
public class UserResource {
    @NotNull
    private String name;
    @NotNull
    private LocalDate birthday;
    @NotNull
    private String countryOfResidence;
    private String phoneNumber;
    private Gender gender;
    private String genderPrecision;

}
