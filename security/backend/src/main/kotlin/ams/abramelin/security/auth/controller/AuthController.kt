package ams.abramelin.security.auth.controller

import ams.abramelin.security.auth.controller.request.DecodeRequest
import ams.abramelin.security.auth.controller.request.LoginRequest
import ams.abramelin.security.auth.controller.request.RegisterRequest
import ams.abramelin.security.auth.controller.response.AuthResponse
import ams.abramelin.security.auth.controller.response.DecodeResponse
import ams.abramelin.security.auth.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@ResponseBody
@RestController
@CrossOrigin(origins = ["http://localhost:8080"])
class AuthController {
    @Autowired
    private lateinit var authService: AuthService

    @PostMapping("/decode")
    fun decode(@RequestBody request: DecodeRequest): DecodeResponse {
        val user = authService.getUserFromToken(request.token)
        return DecodeResponse(user.id, user.roles)
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): AuthResponse {
        val result = authService.login(request.username, request.password)
        return AuthResponse(result.first, result.second)
    }

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): AuthResponse {
        val result = authService.register(request.email, request.username, request.password)
        return AuthResponse(result.first, result.second)
    }
}