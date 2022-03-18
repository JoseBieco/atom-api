package api.atomical.config

import api.atomical.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserDetailsServiceImpl(
    @Autowired
    val db: UserRepository
): UserDetailsService {

    override fun loadUserByUsername(login: String): UserDetails {
        val user =  db.getByEmail(login) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.")

        val roles = arrayOf(user.roles)

        return User
            .builder()
            .username(user.email)
            .password(user.password)
            .roles(*roles)
            .build()
    }
}