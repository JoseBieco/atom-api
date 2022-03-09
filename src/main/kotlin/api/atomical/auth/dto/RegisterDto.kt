package api.atomical.auth.dto

import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class RegisterDto (
    @NotBlank(message = "Name field cannot be blank")
    @NotNull(message = "Name field cannot be null.")
    @Min(value = 2, message = "The name size can't be lower than 2 characters.")
    var name: String,

    @NotBlank(message = "Email field cannot be blank")
    @NotNull(message = "Email field cannot be null.")
    @Min(value = 2, message = "The email size can't be lower than 2 characters.")
    var email: String,

    @NotBlank(message = "Password field cannot be blank")
    @NotNull(message = "Password field cannot be null.")
    @Min(value = 2, message = "The password size can't be lower than 2 characters.")
    @Max(value = 18, message = "The password size can't be bigger than 18 characters.")
    var password: String
)