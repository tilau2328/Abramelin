package ams.abramelin.security.auth.service

import ams.abramelin.security.user.data.UserDao
import ams.abramelin.security.auth.service.dto.UserPrincipalDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.stereotype.Service

@Service
class AuthService {
    @Autowired
    private lateinit var userDao: UserDao

    fun getUserFromToken(token: String): UserPrincipalDto {
        if(!JwtProvider.validateToken(token))
            throw BadCredentialsException("Invalid Token")
        val id = JwtProvider.getIdFromToken(token)
        val user = userDao.getById(id)
        return UserPrincipalDto(user)
    }

    fun login(username: String, password: String): Pair<String, String> {
        val user = userDao.getByUsername(username)
        if(!userDao.comparePassword(password, user.password))
            throw BadCredentialsException("Invalid Password")
        val token = JwtProvider.generateToken(user.id)
        return Pair(user.id, token)
    }

    fun register(email: String, username: String, password: String): Pair<String, String> {
        val user = userDao.create(email, username, password)
        val token = JwtProvider.generateToken(user.id)
        return Pair(user.id, token)
    }
}