package ams.abramelin.tasks.task.domain.commands

import javax.validation.constraints.NotNull

data class RemoveTask(override val id: String,
                      @NotNull val user: String) : TaskCommand