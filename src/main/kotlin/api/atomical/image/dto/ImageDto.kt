package api.atomical.image.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class ImageDto(
    @field:NotBlank(message = "The title cannot be blank")
    @field:NotNull(message = "The title cannot be null.")
    @field:Size(min = 2, message = "The title size can't be lower than 2 characters.")
    var title: String,

    @field:NotBlank(message = "The url cannot be blank")
    @field:NotNull(message = "The url cannot be null.")
    @field:Size(min = 2, message = "The url size can't be lower than 2 characters.")
    var url: String,
) {
}