package ams.abramelin.tasks.task.domain.events

import javax.validation.constraints.NotNull

data class TaskStarred(override val id: String,
                       @NotNull val user: String) : TaskEvent