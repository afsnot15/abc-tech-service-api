package br.com.fiap.abctechservice.repository;

import br.com.fiap.abctechservice.model.Operator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperatorRepository extends JpaRepository<Operator, Long> {

    Optional<Operator> findFirstByRegistration(String registration);
}
