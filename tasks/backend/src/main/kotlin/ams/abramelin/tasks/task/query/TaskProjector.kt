package ams.abramelin.tasks.task.query

import ams.abramelin.tasks.task.domain.events.*
import org.axonframework.eventhandling.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.remove
import org.springframework.stereotype.Component
import java.lang.Exception

@Component
class TaskProjector {
    @Autowired
    private lateinit var operations: MongoTemplate

    @Autowired
    private lateinit var taskRepository: TaskRepository

    @EventHandler
    fun on(event: TaskCreated) {
        val task = TaskEntry(event.id, event.title)
        taskRepository.save(task)
    }

    @EventHandler
    fun on(event: TaskCompleted) {
        getAndUpdate(event.id) {
            it.complete = true
        }
    }

    @EventHandler
    fun on(event: TaskReopened) {
        getAndUpdate(event.id) {
            it.complete = false
        }
    }

    @EventHandler
    fun on(event: TaskTitleUpdated) {
        getAndUpdate(event.id) {
            it.title = event.title
        }
    }

    @EventHandler
    fun on(event: TaskStarred) {
        getAndUpdate(event.id) {
            it.stars++
        }
    }

    @EventHandler
    fun on(event: TaskUnstarred) {
        getAndUpdate(event.id) {
            it.stars--
        }
    }

    private fun getAndUpdate(id: String, call: (task: TaskEntry) -> Unit) {
        val task = operations.findById(id, TaskEntry::class.java)
                ?: throw Exception("Task with id: $id not found")
        call(task)
        operations.save(task)
    }
}

