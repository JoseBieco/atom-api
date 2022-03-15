package api.atomical.jwt

import api.atomical.user.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

@Service
class JwtService(
    @Value("\${security.jwt.expiration}")
    private val expiration: String,

    @Value("\${security.jwt.signatureKey}")
    private val signatureKey: String,
) {
    /**
     * Generate a token based on user's information
     * @param user User entity
     * @return Jwt token
     */
    fun generateToken(user: User): String {
        val expirationTime: LocalDateTime = LocalDateTime.now().plusDays(expiration.toLong())
        val date = Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant())

        return Jwts
            .builder()
            .setIssuer(user.id.toString())
            .setSubject(user.email)
            .setExpiration(date)
            .signWith(SignatureAlgorithm.HS512, signatureKey)
            .compact()
    }

    /**
     * Get information from token
     * @param token User's token
     */
    @Throws(ExpiredJwtException::class)
    fun getClaims(token: String): Claims {
        return Jwts.parser().setSigningKey(signatureKey).parseClaimsJws(token).body
    }

    /**
     * Validate if the token is not expired
     * @param token User's token
     * @return True if the token is not expired, otherwise, false
     */
    fun validToken(token: String): Boolean {
        try {
            val claims: Claims = getClaims(token)
            val date: LocalDateTime = claims.expiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()

            return !LocalDateTime.now().isAfter(date)
        }catch (e: Exception){
            return false
        }
    }

    /**
     * Extract the user email from token
     * @param token User's token
     */
    @Throws(ExpiredJwtException::class)
    fun getUserLogin(token: String): String {
        return getClaims(token).subject
    }
}