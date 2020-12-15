package com.ryulth.worklifebell.api.auth.service

import com.ryulth.worklifebell.api.user.domain.User
import io.jsonwebtoken.*
import mu.KLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.util.Date

@Service
class JwtTokenProvider(
    @Value("\${jwt.secret.base64.access:at6w9z&33&E)H@McQfTjWnZr4u7x!A%D*F}")
    private val accessPrivateKey: String,
    @Value("\${jwt.secret.base64.refresh:at6w9z&33&E)H@McQfTjWnZr4u7x!A%D*F}}")
    private val refreshPrivateKey: String,
    @Value("\${jwt.expiration-seconds.access:10000}")
    private val accessTokenExpirationSeconds: Int,
    @Value("\${jwt.expiration-seconds.refresh:10000}")
    private val refreshTokenExpirationSeconds: Int
) : TokenProvider {
    companion object : KLogging() {
        const val AUTHORITIES_USER_ID = "userId"
        const val AUTHORITIES_LOGIN_TYPE = "loginType"
        const val BEARER = "Bearer"
    }

    override fun generatedToken(user: User): TokenInfo {
        return TokenInfo.of(
            accessToken = newToken(user.id!!, true),
            refreshToken = newToken(user.id!!, false),
            type = BEARER
        )
    }

    private fun newToken(userId: Long, isAccessToken: Boolean): String {
        val now = Date().time
        val validity =
            if (isAccessToken) Date(now + this.accessTokenExpirationSeconds * 1000)
            else Date(now + this.refreshTokenExpirationSeconds * 1000)
        val key = if (isAccessToken) this.accessPrivateKey else this.refreshPrivateKey

        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .claim(AUTHORITIES_USER_ID, userId)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, this.generateKey(key))
            .compact()
    }

    override fun verifyToken(token: String, isAccess: Boolean): Long {
        val key: String = if (isAccess) this.accessPrivateKey else this.refreshPrivateKey
        val jwtToken = token.removePrefix(BEARER).trim()
        var message = ""
        try {
            Jwts.parser().setSigningKey(this.generateKey(key)).parseClaimsJws(jwtToken)
            val claims = Jwts.parser().setSigningKey(this.generateKey(key))
                .parseClaimsJws(jwtToken).body
            return (claims[AUTHORITIES_USER_ID] as Int).toLong()
        } catch (e: SecurityException) {
            logger.error { "Invalid JWT signature trace: $e" }
            message = e.message!!
        } catch (e: MalformedJwtException) {
            logger.error { "Invalid JWT signature trace: $e" }
            message = e.message!!
        } catch (e: ExpiredJwtException) {
            logger.error { "Expired JWT token trace: $e" }
            message = e.message!!
        } catch (e: UnsupportedJwtException) {
            logger.error { "Unsupported JWT token trace: $e" }
            message = e.message!!
        } catch (e: IllegalArgumentException) {
            logger.error { "JWT token compact of handler are invalid trace: $e" }
            message = e.message!!
        } catch (e: JwtException) {
            logger.error { "JWT token are invalid trace: $e" }
            message = e.message!!
        }
        throw BadCredentialsException(message)
    }

    override fun refreshToken(user: User): TokenInfo {
        return this.generatedToken(user)
    }

    private fun generateKey(secretKey: String): ByteArray? {
        return secretKey.toByteArray(StandardCharsets.UTF_8)
    }
}