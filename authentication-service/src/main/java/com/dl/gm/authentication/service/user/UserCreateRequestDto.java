package com.dl.gm.authentication.service.user;


import com.dl.gm.authentication.service.EnumFromValue;
import com.dl.gm.authentication.service.exception.AuthenticationServiceException;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Schema(description = """
        Schema responsible of handling new user creation operation.
        Contains all required information which must be provided to create and save new user in the system.
        """)
class UserCreateRequestDto {

    @NotBlank
    @Size(min = 3, max = 48)
    @Schema(example = "Jan")
    private final String firstName;

    @NotBlank
    @Size(min = 3, max = 48)
    @Schema(example = "Kowalski")
    private final String lastName;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    @Size(max = 48)
    @Schema(example = "kowalski99@gmail.com")
    private final String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")
    @Size(max = 120)
    @Schema(example = "Password1$")
    private final String password;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")
    @Size(max = 120)
    @Schema(example = "Password1$")
    private final String passwordConfirmation;

    @NotNull
    @EnumFromValue(enumClass = UserRole.class)
    private final String userRole;

    UserCreateRequestDto(String firstName, String lastName, String email, String password,
                         String passwordConfirmation, String userRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        this.userRole = userRole;
        validate(this);
    }

    private static void validate(UserCreateRequestDto dto) {
        if (!dto.getPassword().equals(dto.passwordConfirmation)) {
            throw new AuthenticationServiceException("Provided fields password and passwordConfirmation are not same.");
        }
    }
}
