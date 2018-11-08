package ams.abramelin.security.auth.controller.request

data class RegisterRequest(val email: String, val username: String, val password: String)