package com.mecerintered.springbootprojecttwo.Repository;

import com.mecerintered.springbootprojecttwo.Entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Students,Long> {
boolean existsByEmail(String email);
boolean existsByEmailAndIdNot(String email, Long id);
Optional<Students> findByEmail(String email);
List<Students> findByLastName(String lastName);
}
