package api.atomical.auth

import api.atomical.auth.dto.RegisterDto
import api.atomical.user.User
import api.atomical.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class AuthService(
    @Autowired
    val db: UserRepository,

    @Autowired
    val passwordEncoder: PasswordEncoder,
) {
    /**
     * Create new entity
     * @param user RegisterDto
     * @return The created User
     * @throws HttpStatus.BAD_REQUEST This email is already registered
     */
    fun create(user: RegisterDto): User {
        /**
         * Validate email -> must be unique
         * throw error if it's not unique
         */
        return db.getByEmail(user.email)
            .takeIf { it.isEmpty }
            ?.run { User(user).apply { encodePassword(passwordEncoder) }.run { db.save(this) } }
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "This email is already registered")
    }

    /**
     * Remove user from database
     * @param userId User unique identifier
     */
    fun delete(userId: Long) {
        // Validate if the userId exists in database
        return db.findById(userId)
            .orElseThrow{
                ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.")
            }.run { db.delete(this) }
    }
}