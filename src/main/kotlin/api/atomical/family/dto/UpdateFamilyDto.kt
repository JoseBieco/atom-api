package api.atomical.family.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class UpdateFamilyDto(
    @field:NotBlank(message = "The description cannot be blank")
    @field:NotNull(message = "The description cannot be null.")
    @field:Size(min = 2, message = "The description size can't be lower than 2 characters.")
    var description: String,
)