package br.com.fiap.tds.twotdspj.javaadv.taskManager.datasource.repository;

import br.com.fiap.tds.twotdspj.javaadv.taskManager.domainmodel.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>{
}
