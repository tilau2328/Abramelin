package ams.abramelin.tasks.task.domain.events

import javax.validation.constraints.NotNull

data class TaskUnstarred(override val id: String,
                         @NotNull val user: String) : TaskEvent