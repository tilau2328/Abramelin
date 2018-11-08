package ams.abramelin.tasks.task.domain.events

import javax.validation.constraints.NotNull

data class TaskReopened(override val id: String,
                        @NotNull val user: String) : TaskEvent