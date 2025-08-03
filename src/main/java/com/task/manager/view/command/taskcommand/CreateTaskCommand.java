package com.task.manager.view.command.taskcommand;


import com.task.manager.domain.dto.TaskDTO;
import com.task.manager.domain.mapper.EntityMapper;
import com.task.manager.domain.model.Task;
import com.task.manager.service.TaskSevice;
import com.task.manager.view.command.Command;

public class CreateTaskCommand implements Command<TaskDTO> {
    private TaskDTO inputTaskDTO;
    private TaskSevice taskSevice;
    private EntityMapper<Task, TaskDTO> taskMapper;

    @Override
    public TaskDTO execute() {
        Task task = taskSevice.create(inputTaskDTO);
        return taskMapper.toDto(task);
    }
    
}
