package ams.abramelin.tasks.task.domain.commands

import org.axonframework.commandhandling.TargetAggregateIdentifier
import javax.validation.constraints.NotNull

data class UpdateTaskTitle(@TargetAggregateIdentifier
                           override val id: String,
                           @NotNull val user: String,
                           @NotNull val title: String) : TaskCommand