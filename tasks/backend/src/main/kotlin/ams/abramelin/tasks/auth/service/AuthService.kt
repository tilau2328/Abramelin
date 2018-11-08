package ams.abramelin.tasks.auth.service

import ams.abramelin.tasks.auth.service.dto.TokenDto
import ams.abramelin.tasks.auth.service.dto.UserDto
import ams.abramelin.tasks.auth.service.dto.UserPrincipalDto
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate



@Service
class AuthService {
    fun getUserFromToken(token: String): UserPrincipalDto {
        val restTemplate = RestTemplate()
        val user = restTemplate.postForObject("http://localhost:9000/decode", TokenDto(token), UserDto::class.java)
                ?: throw BadCredentialsException("Invalid Token")
        return UserPrincipalDto(user)
    }
}