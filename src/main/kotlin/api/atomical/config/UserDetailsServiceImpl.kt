package api.atomical.config

import api.atomical.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    @Autowired
    val db: UserRepository
): UserDetailsService {

    override fun loadUserByUsername(login: String): UserDetails {
        val user =  db.getByEmail(login)
            .orElseThrow { UsernameNotFoundException("User not found!") }

        /**
         * TODO
         */
        val roles = arrayOf(user.roles)

        return User
            .builder()
            .username(user.email)
            .password(user.password)
            .roles(*roles)
            .build()
    }
}