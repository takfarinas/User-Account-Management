package com.technical.test.service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String name;
    private LocalDate birthday;
    private String countryOfResidence;
    private String phoneNumber;
    private Gender gender;
    private String genderPrecision;
}
