package api.atomical.atom.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class CreateAtomDto(
    @field:NotBlank(message = "Name field cannot be blank")
    @field:NotNull(message = "Name field cannot be null.")
    @field:Size(min = 2, message = "The name size can't be lower than 2 characters.")
    var name: String,

    var familyId: Long?,
    var atomImage: Long?,
    var image: Long?,

    @field:NotNull(message = "Number field cannot be null")
    var number: Int,

    @field:NotNull(message = "Weigh field cannot be null")
    var weigh: Float,

    @field:NotBlank(message = "the symbol field cannot be blank")
    @field:NotNull(message = "The symbol field cannot be null.")
    var symbol: String,

    var description: String? = null,
    var curiosity: String? = null,

    @field:NotNull(message = "Column field cannot be null")
    var column: Int,

    @field:NotNull(message = "Period field cannot be null")
    var period: Int,
)