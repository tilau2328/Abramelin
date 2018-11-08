package ams.abramelin.tasks.task.domain

import ams.abramelin.tasks.task.domain.commands.*
import ams.abramelin.tasks.task.domain.events.*
import ams.abramelin.tasks.task.domain.exceptions.TaskAlreadyCompleted
import ams.abramelin.tasks.task.domain.exceptions.TaskHasNotStarred
import ams.abramelin.tasks.task.domain.exceptions.TaskHasStarred
import ams.abramelin.tasks.task.domain.exceptions.TaskNotCompleted
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.model.AggregateIdentifier
import org.axonframework.commandhandling.model.AggregateLifecycle
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.spring.stereotype.Aggregate
import javax.validation.constraints.NotNull

@Aggregate
class TaskAggregate() {
    @AggregateIdentifier
    var id: String? = null
    @NotNull
    var title: String? = null
    @NotNull
    var completed: Boolean = false
    @NotNull
    var stars: ArrayList<String> = arrayListOf()

    companion object {
        private const val serialVersionUID = -5977984483620451665L
    }

    // Command Handlers

    @CommandHandler
    constructor(command: CreateTask): this() {
        AggregateLifecycle.apply(TaskCreated(command.id, command.user, command.title))
    }

    @CommandHandler
    fun on(command: CreateTask) {
        AggregateLifecycle.apply(TaskCreated(command.id, command.user, command.title))
    }

    @CommandHandler
    fun on(command: CompleteTask) {
        assertNotCompleted()
        AggregateLifecycle.apply(TaskCompleted(command.id, command.user))
    }

    @CommandHandler
    fun on(command: ReopenTask) {
        assertCompleted()
        AggregateLifecycle.apply(TaskReopened(command.id, command.user))
    }

    @CommandHandler
    fun on(command: StarTask) {
        assertUserHasNotStarred(command.user)
        AggregateLifecycle.apply(TaskStarred(command.id, command.user))
    }

    @CommandHandler
    fun on(command: UnstarTask) {
        assertUserHasStarred(command.user)
        AggregateLifecycle.apply(TaskUnstarred(command.id, command.user))
    }

    @CommandHandler
    fun on(command: UpdateTaskTitle) {
        assertNotCompleted()
        AggregateLifecycle.apply(TaskTitleUpdated(command.id, command.user, command.title))
    }

    // Event Handlers

    @EventSourcingHandler
    fun on(event: TaskCreated) {
        this.id = event.id
        this.title = event.title
    }

    @EventSourcingHandler
    fun on(event: TaskCompleted) {
        this.completed = true
    }

    @EventSourcingHandler
    fun on(event: TaskReopened) {
        this.completed = false
    }

    @EventSourcingHandler
    fun on(event: TaskStarred) {
        this.stars.add(event.user)
    }

    @EventSourcingHandler
    fun on(event: TaskUnstarred) {
        this.stars.remove(event.user)
    }

    // Validators

    private fun assertNotCompleted() {
        if (completed) {
            throw TaskAlreadyCompleted("Task [$id] is completed.")
        }
    }

    private fun assertCompleted() {
        if (!completed) {
            throw TaskNotCompleted("Task [$id] is not completed.")
        }
    }

    private fun assertUserHasNotStarred(user: String) {
        if (this.stars.indexOf(user) > -1) {
            throw TaskHasStarred("User [$user] has already starred Task [$id].")
        }
    }

    private fun assertUserHasStarred(user: String) {
        if (this.stars.indexOf(user) == -1) {
            throw TaskHasNotStarred("User [$user] hasn't starred Task [$id] yet.")
        }
    }
}