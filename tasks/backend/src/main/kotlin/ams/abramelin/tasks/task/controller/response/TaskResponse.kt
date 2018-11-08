package ams.abramelin.tasks.task.controller.response

import ams.abramelin.tasks.task.query.TaskEntry

data class TaskResponse(val tasks: List<TaskEntry>)