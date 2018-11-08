package ams.abramelin.tasks.task.controller

import ams.abramelin.tasks.auth.service.dto.UserPrincipalDto
import ams.abramelin.tasks.task.controller.request.CreateTaskRequest
import ams.abramelin.tasks.task.controller.request.UpdateTaskTitleRequest
import ams.abramelin.tasks.task.query.TaskEntry
import ams.abramelin.tasks.task.service.TaskService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@CrossOrigin(origins = ["http://localhost:8080"])
class TasksController {
    @Autowired
    private lateinit var taskService: TaskService

    var logger = LoggerFactory.getLogger(TasksController::class.java)

    @GetMapping("/tasks")
    @ResponseBody
    fun listTasks(): List<TaskEntry> {
        return taskService.list()
    }

    @PostMapping("/tasks")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    fun createTask(auth: Authentication, @RequestBody @Valid request: CreateTaskRequest) {
        val user= getIdFromAuth(auth)
        taskService.create(user, request.title)
    }

    @PostMapping("/tasks/{id}/title")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    fun updateTaskTitle(auth: Authentication, @PathVariable id: String, @RequestBody @Valid request: UpdateTaskTitleRequest) {
        val user= getIdFromAuth(auth)
        taskService.updateTaskTitle(id, user, request.title)
    }


    @PostMapping("/tasks/{id}/complete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    fun completeTask(auth: Authentication, @PathVariable id: String) {
        val user= getIdFromAuth(auth)
        taskService.completeTask(id, user)
    }


    @PostMapping("/tasks/{id}/reopen")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    fun reopenTask(auth: Authentication, @PathVariable id: String) {
        val user= getIdFromAuth(auth)
        taskService.reopenTask(id, user)
    }


    @PostMapping("/tasks/{id}/star")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    fun starTask(auth: Authentication, @PathVariable id: String) {
        val user= getIdFromAuth(auth)
        taskService.starTask(id, user)
    }


    @PostMapping("/tasks/{id}/unstar")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    fun unstarTask(auth: Authentication, @PathVariable id: String) {
        val user= getIdFromAuth(auth)
        taskService.unstarTask(id, user)
    }

    private fun getIdFromAuth(auth: Authentication): String {
        val user = (auth.principal as UserPrincipalDto?)
            ?: throw BadCredentialsException("Invalid User")
        return user.id
    }
}