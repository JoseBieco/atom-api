package api.atomical.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.staticProperties

@Service
class UserService(
    @Autowired
    val db: UserRepository,

    @Autowired
    val passwordEncoder: PasswordEncoder,
) {

    /**
     * Get all users from database that are active
     */
    fun getAll(pageable: PageRequest): Page<User> {
        return db.getAll(pageable)
    }

    /**
     * Alter user deleted_at attribute
     * Checks if the userId param exists, if not, throws bad request
     * @param userId Represents the user unique key
     */
    fun softDelete(userId: Long): User {
       return db.findById(userId)
           .orElseThrow {
               ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.")
           }.apply { deleted_at = LocalDateTime.now() }.run { db.save(this) }
    }

    /**
     * Split the request body JSON into "dict" and then update the user data
     * @param userId User unique identifier
     * @param requestBody Request body data
     */
    fun update(userId: Long, requestBody: String) {
        val fields = requestBody
            .replace("{", "")
            .replace("}", "")
            .split(",")

        fields.forEach {
            val updateData = it.split(":")
            updateField(userId, updateData[0], updateData[1])
        }
    }

    /**
     * Responsible to update the user field on database
     * @param id Identifier from user
     * @param field Property from user
     * @param value New value for the property
     */
    fun updateField(id: Long, field: String, value: String) {
      // return db.updateField(id, field, value)
    }
}