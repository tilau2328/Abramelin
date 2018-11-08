package ams.abramelin.tasks.task.query

import com.mongodb.lang.NonNull
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "tasks")
data class TaskEntry(
    @Id
    var id: String,
    @NonNull
    var title: String
) {
    @Version
    var version: Long = 0
    var complete: Boolean = false
    var stars: Int = 0
}