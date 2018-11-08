package ams.abramelin.tasks.task.service

import ams.abramelin.tasks.task.domain.commands.*
import ams.abramelin.tasks.task.query.TaskEntry
import ams.abramelin.tasks.task.query.TaskRepository
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.common.IdentifierFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TaskService {
    private val identifierFactory = IdentifierFactory.getInstance()

    @Autowired
    private lateinit var taskEntryEntryRepository: TaskRepository

    @Autowired
    private lateinit var commandGateway: CommandGateway

    fun list(): List<TaskEntry> {
        return taskEntryEntryRepository.findAll().toList()
    }

    fun create(user: String, title: String) {
        commandGateway.send<Unit>(CreateTask(identifierFactory.generateIdentifier(), user, title))
    }

    fun updateTaskTitle(id: String, user: String, title: String) {
        commandGateway.send<Unit>(UpdateTaskTitle(id, user, title))
    }

    fun completeTask(id: String, user: String) {
        commandGateway.send<Unit>(CompleteTask(id, user))
    }

    fun reopenTask(id: String, user: String) {
        commandGateway.send<Unit>(ReopenTask(id, user))
    }

    fun starTask(id: String, user: String) {
        commandGateway.send<Unit>(StarTask(id, user))
    }

    fun unstarTask(id: String, user: String) {
        commandGateway.send<Unit>(UnstarTask(id, user))
    }
}