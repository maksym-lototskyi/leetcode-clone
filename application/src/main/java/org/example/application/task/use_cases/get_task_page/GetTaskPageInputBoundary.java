package org.example.application.task.use_cases.get_task_page;

import org.example.domain.task.TaskSummary;

import java.util.List;

public interface GetTaskPageInputBoundary {
    List<TaskSummary> execute(int pageNumber, int pageSize);
}
