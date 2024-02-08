package xyz.strashi.ruestarmel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.strashi.ruestarmel.model.DBUser;

public interface DBUserRepository extends JpaRepository<DBUser,Integer> {

    DBUser findByUsername(String username);
}
