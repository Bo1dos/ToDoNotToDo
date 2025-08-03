package com.task.manager.domain.dto;

import java.time.Instant;
import java.util.UUID;

import com.task.manager.domain.model.Task.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TaskDTO {

    UUID id;
    
    String header;


    String description;


    Instant creationAt;


    Instant deadLine; 
    

    TaskStatus taskStatus;

}
