package ams.abramelin.tasks.task.domain.commands

import org.axonframework.commandhandling.TargetAggregateIdentifier
import javax.validation.constraints.NotNull

data class UnstarTask(@TargetAggregateIdentifier
                      override val id: String,
                      @NotNull val user: String) : TaskCommand