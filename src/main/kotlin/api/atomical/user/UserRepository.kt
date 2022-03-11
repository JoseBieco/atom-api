package api.atomical.user

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface UserRepository: JpaRepository<User, Long> {

    /**
     * Get user by email
     * @param email String
     * @return Optional of user
     */
    fun getByEmail(email: String): Optional<User>

    /**
     * Get all users pageable
     * @param pageRequest Pageable
     * @return Page of users
     */
    @Query("SELECT u FROM User u WHERE u.deleted_at IS NULL")
    fun getAll(pageRequest: Pageable): Page<User>
}