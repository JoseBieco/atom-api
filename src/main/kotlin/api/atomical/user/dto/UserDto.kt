package api.atomical.user.dto

import api.atomical.auth.dto.LoginDto
import api.atomical.user.User

/**
 * Use to return  user on the response.
 * Remove the password field
 */
class UserDto (
    var id: Long? = null,
    var name: String? = null,
    var email: String? = null
){
    constructor(user: User): this() {
        this.apply {
            id = user.id
            name = user.name
            email = user.email
        }
    }
}