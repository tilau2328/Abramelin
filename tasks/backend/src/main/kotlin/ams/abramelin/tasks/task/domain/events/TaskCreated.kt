package ams.abramelin.tasks.task.domain.events

import javax.validation.constraints.NotNull

data class TaskCreated(override val id: String,
                       @NotNull val user: String,
                       @NotNull val title: String) : TaskEvent