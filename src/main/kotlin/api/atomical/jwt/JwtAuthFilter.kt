package api.atomical.jwt

import api.atomical.auth.AuthService
import api.atomical.config.UserDetailsServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthFilter(
    @Autowired
    val jwtService: JwtService,

    @Autowired
    val userDetailsService: UserDetailsServiceImpl
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val authorization = if(request.getHeader("Authorization").isNullOrBlank()) null else request.getHeader("Authorization")

        if(!authorization.isNullOrBlank() && authorization.startsWith("Bearer")) {
            val token = authorization.split(" ")[1]
            val isValid: Boolean = jwtService.validToken(token)

            if(isValid) {
                val userDetails: UserDetails = userDetailsService.loadUserByUsername(jwtService.getUserLogin(token))

                val user: UsernamePasswordAuthenticationToken =
                    UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                user.details = (WebAuthenticationDetailsSource().buildDetails(request))
                SecurityContextHolder.getContext().authentication = user
            }
        }
        filterChain.doFilter(request, response)
    }

}