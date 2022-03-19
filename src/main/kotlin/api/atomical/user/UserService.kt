package api.atomical.user

import api.atomical.user.dto.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

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
           }.apply { deletedAt = LocalDateTime.now() }.run { db.save(this) }
    }

    /**
     * TODO:
     * Update user's name
     * @param userId User unique identifier
     * @param updatedUser Request body data
     */
    fun update(userId: Long, updatedUser: UserDto): User {
        val user = db.findById(userId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.")
        }
        user.name = updatedUser.name

        return db.save(user)
    }

    /**
     * Get user from database by id
     */
    fun getById(userId: Long): User {
        return db.findById(userId)
            .orElseThrow {
                ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.")
            }
    }
}