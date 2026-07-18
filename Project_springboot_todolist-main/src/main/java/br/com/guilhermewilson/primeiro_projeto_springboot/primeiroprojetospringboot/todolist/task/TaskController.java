package br.com.guilhermewilson.primeiro_projeto_springboot.primeiroprojetospringboot.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")

    public class TaskController {

        @Autowired
        private ITaskRepository taskRepository;

        @PostMapping("/")
        public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
            var idUser = request.getAttribute("idUser");
            taskModel.setIdUser((UUID) idUser);

            var currentDate = LocalDateTime.now();
            // 10/11/2023 - Current
            // 10/10/2023 - startAt

            // PRIMEIRO IF: Valida se as datas estão no passado (Comparando com a data atual)
            if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("A data inserida de início / data de término deve ser maior do que a data atual");
            }

            // SEGUNDO IF: Valida a ordem das datas (Comparando início com o fim)
            if (taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("A data de início deve ser menor do que a data de término");
            }

            var task = this.taskRepository.save(taskModel);
            return ResponseEntity.status(HttpStatus.OK).body(task);
        }

        @GetMapping("/")
        public void list(HttpServletRequest request) {
            var idUser = request.getAttribute("idUser");
            this.taskRepository.findByIdUser((UUID) idUser);
        }

    }