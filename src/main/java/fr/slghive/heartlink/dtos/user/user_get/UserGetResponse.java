package fr.slghive.heartlink.dtos.user.user_get;

import java.time.LocalDate;

public record UserGetResponse(
                Integer id,
                LocalDate birthday,
                String firstname,
                String lastname,
                String phone) {
}
