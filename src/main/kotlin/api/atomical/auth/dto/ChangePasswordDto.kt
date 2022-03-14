package api.atomical.auth.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class ChangePasswordDto (
    @field:NotBlank(message = "Password field cannot be blank")
    @field:NotNull(message = "Password field cannot be null.")
    @field:Size(min = 2, message = "The password size can't be lower than 2 characters.")
    var password: String
)