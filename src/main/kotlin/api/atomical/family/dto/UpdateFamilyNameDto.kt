package api.atomical.family.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class UpdateFamilyNameDto(
    @field:NotBlank(message = "Name field cannot be blank")
    @field:NotNull(message = "Name field cannot be null.")
    @field:Size(min = 2, message = "The name size can't be lower than 2 characters.")
    var name: String
)