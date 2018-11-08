package ams.abramelin.tasks.task.domain.events

import javax.validation.constraints.NotNull

data class TaskRemoved(override val id: String,
                       @NotNull val user: String) : TaskEvent