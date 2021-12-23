package com.danikarasawa.todolist.controller;

import com.danikarasawa.todolist.model.Task;
import com.danikarasawa.todolist.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@Tag(name = "To do List", description = "App de gerenciamento de tarefas")
@RequestMapping("/api/v1")
public class TaskController {

    TaskService taskService;

    @Operation(summary = "Criando uma nova tarefa", description = "conteúdo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Houve um erro ao criar a tarefa. Verifique as infos")
    })

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask (@RequestBody Task task) {
        log.info("Criando uma nova tarefa com as infos [{}]", task);
        return taskService.createTask(task);
    }

    @Operation(summary = "Buscando uma lista de tarefas", description = "dd")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Houve um erro ao buscar a lista de tarefas. Verifique as infos")
    })
    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasks() {
        log.info("Listando todas as tarefas cadastradas");
        return taskService.listAllTasks();
    }

//    @ApiOperation(value = "Buscando uma tarefa")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Busca realizada com sucesso"),
//            @ApiResponse(code = 404, message = "Houve um erro ao buscar a tarefa. Verifique as infos")
//    })
    @GetMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> getTaskById(@PathVariable (value = "id") Long id) {
        log.info("Buscando a tarefa a partir de seu ID [{}]", id);
        return taskService.findTaskById(id);
    }

//    @ApiOperation(value = "Atualizando uma tarefa")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Tarefa atualizada com sucesso"),
//            @ApiResponse(code = 404, message = "Houve um erro ao atualizar a tarefa. Verifique as infos")
//    })
    @PutMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> updateTaskById(@PathVariable (value = "id") Long id, @RequestBody Task task) {
        log.info("Atualizando a nova tarefa com o id [{}] e as informações são [{}]", id, task);
        return taskService.updateTaskById(task, id);
    }

//    @ApiOperation(value = "Criando uma nova tarefa")
//    @ApiResponses(value = {
//            @ApiResponse(code = 204, message = "Tarefa excluída com sucesso"),
//            @ApiResponse(code = 404, message = "Houve um erro ao excluir a tarefa. Verifique as infos")
//    })
    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteTaskById(@PathVariable (value = "id") Long id) {
        log.info("Excluindo uma nova tarefa com o ID [{}]", id);
        return taskService.deleteTaskById(id);
    }
}
