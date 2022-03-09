package api.atomical.config

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
class WebSecurityConfig: WebSecurityConfigurerAdapter() {
    /**
     * Set the encoder default bcrypt version
     */
    @Bean
    fun encoder(): PasswordEncoder {
        return BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.`$2A`)
    }
}