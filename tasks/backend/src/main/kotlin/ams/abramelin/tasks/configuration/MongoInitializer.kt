package ams.abramelin.tasks.configuration

import ams.abramelin.tasks.task.query.TaskRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class MongoInitializer(val repository: TaskRepository) : ApplicationRunner {
    @Throws(Exception::class)
    override fun run(args: ApplicationArguments) {
        repository.deleteAll()
    }
}