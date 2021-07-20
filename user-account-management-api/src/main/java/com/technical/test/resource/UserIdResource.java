package com.technical.test.resource;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper=true)
public class UserIdResource extends UserResource {
    private String id;
}
