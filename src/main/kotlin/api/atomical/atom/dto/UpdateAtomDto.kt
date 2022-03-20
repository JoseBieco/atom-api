package api.atomical.atom.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class UpdateAtomDto(
    @field:NotBlank(message = "The name field cannot be blank")
    @field:NotNull(message = "The name field cannot be null.")
    var name: String,

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