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
        if(db.getByEmail(user.email) !== null) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "This email is already registered")
        }

        val createUser = User(user)
        createUser.encodePassword(passwordEncoder)
        return this.db.save(createUser)
    }
}