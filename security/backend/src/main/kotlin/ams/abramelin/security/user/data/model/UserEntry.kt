package ams.abramelin.security.user.data.model

import com.mongodb.lang.NonNull
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "user")
class UserEntry
    @PersistenceConstructor
    constructor(
        @Indexed(unique = true)
        var email: String,
        @Indexed(unique = true)
        var username: String,
        @NonNull
        var password: String
    ) {
    @Id
    lateinit var id: String
    @Version
    var version: Long = 0
    var roles: List<String> = emptyList()
}
