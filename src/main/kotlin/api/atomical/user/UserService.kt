package api.atomical.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

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
}