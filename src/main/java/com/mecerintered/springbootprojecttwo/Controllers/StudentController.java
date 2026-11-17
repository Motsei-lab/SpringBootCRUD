package com.mecerintered.springbootprojecttwo.Controllers;

import com.mecerintered.springbootprojecttwo.DTO.StudentRequest;
import com.mecerintered.springbootprojecttwo.DTO.StudentResponse;
import com.mecerintered.springbootprojecttwo.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentRequest request){
        StudentResponse response = studentService.createStudent(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentRequest request){
        return ResponseEntity.ok(studentService.updateStudent(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<StudentResponse> getStudentByEmail(@RequestParam String email){
        return ResponseEntity.ok(studentService.getStudentByEmail(email));
    }

    @GetMapping("/search/last_name")
    public ResponseEntity <List<StudentResponse>> getStudentBySurname(@RequestParam String lastName){
        return ResponseEntity.ok(studentService.getStudentByLastName(lastName));
    }
}
