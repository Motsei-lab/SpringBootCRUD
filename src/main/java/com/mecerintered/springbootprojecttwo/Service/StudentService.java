package com.mecerintered.springbootprojecttwo.Service;

import com.mecerintered.springbootprojecttwo.DTO.StudentRequest;
import com.mecerintered.springbootprojecttwo.DTO.StudentResponse;
import com.mecerintered.springbootprojecttwo.Entity.Students;
import com.mecerintered.springbootprojecttwo.Repository.StudentRepository;
import com.mecerintered.springbootprojecttwo.exceptions.DuplicateResourceException;
import com.mecerintered.springbootprojecttwo.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public StudentResponse createStudent(StudentRequest request) {
        if (studentRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("A student with this email already exists");
        }
        Students stu = new Students();
        stu.setFirstName(request.firstName());
        stu.setLastName(request.lastName());
        stu.setEmail(request.email());

        Students savedStu = studentRepository.save(stu);

        return mapToResponse(savedStu);
    }

    //Read All
    @Transactional(readOnly = true)
    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    //Read One
    @Transactional(readOnly = true)
    public StudentResponse getStudentById(Long id) {
        Students stu = findStudentOrThrow(id);
        return mapToResponse(stu);

    }

    //Update
    @Transactional
    public StudentResponse updateStudent(Long id, StudentRequest request) {
        Students stu = findStudentOrThrow(id);

        boolean emailUsedByAnotherStudent =
                studentRepository.existsByEmailAndIdNot(request.email(), id);

        if (emailUsedByAnotherStudent) {
            throw new DuplicateResourceException("Another student is already using this email");
        }

        stu.setFirstName(request.firstName());
        stu.setLastName(request.lastName());
        stu.setEmail(request.email());
        Students updatedStudent = studentRepository.save(stu);
        return mapToResponse(updatedStudent);
    }

    //Delete
    @Transactional
    public void deleteStudent(Long id) {
        Students student = findStudentOrThrow(id);

        studentRepository.delete(student);
    }

    private Students findStudentOrThrow(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student with ID: " + id + " was not found"));
    }

    private StudentResponse mapToResponse(Students student){
        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
    }
}
