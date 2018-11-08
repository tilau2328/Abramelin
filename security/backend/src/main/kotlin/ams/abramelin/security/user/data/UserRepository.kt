package ams.abramelin.security.user.data

import ams.abramelin.security.user.data.model.UserEntry
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: MongoRepository<UserEntry, String> {
    fun findByUsername(username: String): UserEntry?
}