package ams.abramelin.tasks.task.domain.exceptions

open class TaskException(message: String) : RuntimeException(message) {
    companion object {
        private const val serialVersionUID = 1518440584190922771L
    }
}
