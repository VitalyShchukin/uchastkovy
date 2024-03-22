package ru.mis.uchastkovy.security.repo;

import org.springframework.data.repository.CrudRepository;
import ru.mis.uchastkovy.security.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByLogin(String login);
}
