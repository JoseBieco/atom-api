package api.atomical.user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository: JpaRepository<User, Long> {

    /**
     * Get user by email
     * @param email String
     * @return Optional of user
     */
    fun getByEmail(email: String): User?
}