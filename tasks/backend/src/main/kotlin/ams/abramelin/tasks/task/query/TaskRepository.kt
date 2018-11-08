package ams.abramelin.tasks.task.query

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository: MongoRepository<TaskEntry, String>