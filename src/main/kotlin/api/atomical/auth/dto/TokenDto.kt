package api.atomical.auth.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class TokenDto(
    @field:NotBlank(message = "The token cannot be blank")
    @field:NotNull(message = "The token cannot be null.")
    val token: String
) {
}