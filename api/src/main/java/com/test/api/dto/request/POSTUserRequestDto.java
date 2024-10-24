package com.test.api.dto.request;

import com.test.api.user.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class POSTUserRequestDto {

    @NotEmpty(message = "Login must not be empty or null")
    @Size(
            min = 11,
            max = 50,
            message = "Min size is 11, max size is 50"
    )
    @Email(message = "Entered non email")
    @Schema(description = "user login", example = "iii@gmail.com", minLength = 11, maxLength = 50)
    private String login;

//    ^(?=.*\d{3,})(?=.*[^A-Za-z0-9])[\S]{7,20}$

    @Pattern(regexp = "^(?=(?:.*\\d.*){3,})(?=.*[^A-Za-z0-9])[A-Za-z\\d[^A-Za-z0-9]]{7,20}$", message = "Non valid password (must be digit 3 or more, special char 1 or more, length 7-20)")
    @NotEmpty(message = "Password must not be empty or null")
    @Schema(description = "user password: digit 3 or more, special char 1 or more, length 7-20", example = "qqq_111", minLength = 7, maxLength = 20)
    private String password;

    @Size(
            max = 256,
            min = 1,
            message = "Max size is 256"
    )
    @NotEmpty(message = "Full name must not be empty or null)")
    @Schema(description = "user full name", example = "Ole Szhaf", minLength = 1, maxLength = 256)
    private String fullName;

    @Pattern(regexp = "^(male|female|none)$", message = "Gender name must be only male, female or none")
    @Schema(description = "user gender: male, female or none", example = "female", minLength = 4, maxLength = 6)
    @NotNull(message = "Gender name must not be null")
    private String genderName;

}
