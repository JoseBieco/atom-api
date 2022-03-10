package api.atomical.auth.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class RegisterDto (
    @field:NotBlank(message = "Name field cannot be blank")
    @field:NotNull(message = "Name field cannot be null.")
    @field:Size(min = 2, message = "The name size can't be lower than 2 characters.")
    var name: String,

    @field:Email(message = "The email must be valid.")
    var email: String,

    @field:NotBlank(message = "Password field cannot be blank")
    @field:NotNull(message = "Password field cannot be null.")
    @field:Size(min = 2, message = "The password size can't be lower than 2 characters.")
    var password: String
)