package ams.abramelin.security.user.data

import ams.abramelin.security.user.data.model.UserEntry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.lang.Exception
import org.springframework.data.mongodb.core.query.Update



@Component
class UserDao {
    @Autowired
    private lateinit var operations: MongoTemplate

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    fun create(email: String, username: String, password: String): UserEntry {
        val user = UserEntry(email, username, passwordEncoder.encode(password))
        return userRepository.insert(user)
    }

    fun getById(id: String): UserEntry {
        return operations.findById(id, UserEntry::class.java)
            ?: throw Exception("User with id: $id not found")
    }

    fun getByUsername(username: String): UserEntry {
        return userRepository.findByUsername(username)
            ?: throw Exception("User with username: $username not found")
    }

    fun update(id: String, email: String?, username: String?, password: String?): UserEntry {
        val user = operations.findById(id, UserEntry::class.java)
            ?: throw Exception("User with id: $id not found")

        if(email != null)
            user.email = email
        if(username != null)
            user.username = username
        if(password != null)
            user.password = passwordEncoder.encode(password)
        return operations.save(user)
    }

    fun delete(id: String) {
        val user = operations.findById(id, UserEntry::class.java)
                ?: throw Exception("User with id: $id not found")
        operations.remove(user)
    }

    fun comparePassword(password: String, encoded: String): Boolean {
        return passwordEncoder.matches(password, encoded)
    }
}