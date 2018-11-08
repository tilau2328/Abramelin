package ams.abramelin.security.user.service

import ams.abramelin.security.user.data.UserDao
import ams.abramelin.security.user.data.model.UserEntry
import ams.abramelin.security.user.service.dto.UpdateUserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {
    @Autowired
    private lateinit var userDao: UserDao

    fun find(id: String): UserEntry {
        return userDao.getById(id)
    }

    fun update(id: String, updates: UpdateUserDto): UserEntry {
        return userDao.update(id, updates.email, updates.username, updates.password)
    }

    fun delete(id: String) {
        return userDao.delete(id)
    }
}