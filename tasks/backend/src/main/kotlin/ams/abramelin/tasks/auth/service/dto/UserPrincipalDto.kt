package ams.abramelin.tasks.auth.service.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors

class UserPrincipalDto(user: UserDto): UserDetails {
    private val authorities: List<GrantedAuthority>
    private val username = ""
    private val password = ""
    private val roles = user.roles
    val id = user.id

    init {
        authorities = roles.stream().map {
            role -> SimpleGrantedAuthority(role)
        }.collect(Collectors.toList<GrantedAuthority>())
    }

    override fun getUsername(): String {
        return username
    }

    override fun getPassword(): String {
        return password
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }
}
