package api.atomical.auth.dto

import api.atomical.user.User
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import javax.persistence.Column

class LoggedDto(
    var id: Long? = null,
    var name: String? = null,
    var email: String? = null,
    var token: String? = null,
    var roles: String? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var createdAt: LocalDateTime? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var updatedAt: LocalDateTime? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var deletedAt: LocalDateTime? = null,

) {
    constructor(user: User): this() {
        this.apply {
            id = user.id
            name = user.name
            email = user.email
            token = user.token
            roles = user.roles
            createdAt = user.createdAt
            updatedAt = user.updatedAt
            deletedAt = user.deletedAt
        }
    }
}