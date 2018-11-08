package ams.abramelin.security.auth.service

import io.jsonwebtoken.*
import org.slf4j.LoggerFactory
import java.util.*

object JwtProvider {
    private val logger = LoggerFactory.getLogger(JwtProvider::class.java)

    private const val secret = "OhHiIAmASecretDontTellAnyoneAboutMe"
    private const val expiration = 60 * 60 * 1000 * 24

    fun generateToken(id: String): String {
        return Jwts.builder()
                .setSubject(id)
                //.setPayload()
                .setIssuedAt(Date())
                .setExpiration(Date(Date().time + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact()
    }

    fun getIdFromToken(token: String): String {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .body.subject
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
            return true
        } catch (e: SignatureException) {
            logger.error("Invalid JWT signature -> Message: {} ", e)
        } catch (e: MalformedJwtException) {
            logger.error("Invalid JWT token -> Message: {}", e)
        } catch (e: ExpiredJwtException) {
            logger.error("Expired JWT token -> Message: {}", e)
        } catch (e: UnsupportedJwtException) {
            logger.error("Unsupported JWT token -> Message: {}", e)
        } catch (e: IllegalArgumentException) {
            logger.error("JWT claims string is empty -> Message: {}", e)
        }

        return false
    }
}