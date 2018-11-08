package ams.abramelin.tasks.task.domain.commands

import javax.validation.constraints.NotNull

data class CreateTask(override val id: String,
                      @NotNull val user: String,
                      @NotNull val title: String) : TaskCommand