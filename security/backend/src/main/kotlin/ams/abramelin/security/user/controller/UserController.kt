package ams.abramelin.security.user.controller

import ams.abramelin.security.auth.service.dto.UserPrincipalDto
import ams.abramelin.security.user.controller.response.UserResponse
import ams.abramelin.security.user.controller.request.UpdateUserRequest
import ams.abramelin.security.user.service.UserService
import ams.abramelin.security.user.service.dto.UpdateUserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*


@ResponseBody
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = ["http://localhost:8080"])
class UserController {
    @Autowired
    private lateinit var userService: UserService

    @GetMapping
    fun find(auth: Authentication): UserResponse {
        val id = getIdFromAuth(auth)
        val user = userService.find(id)
        return UserResponse(id, user.email, user.username)
    }

    @PostMapping
    fun update(auth: Authentication, @RequestBody data: UpdateUserRequest): UserResponse {
        val id = getIdFromAuth(auth)
        val user = userService.update(id, UpdateUserDto(data.email, data.username, data.password))
        return UserResponse(id, user.email, user.username)
    }

    @DeleteMapping
    fun delete(auth: Authentication) {
        val id = getIdFromAuth(auth)
        userService.delete(id)
    }

    private fun getIdFromAuth(auth: Authentication): String {
        val user = (auth.principal as UserPrincipalDto?)
            ?: throw BadCredentialsException("Invalid User")
        return user.id
    }
}