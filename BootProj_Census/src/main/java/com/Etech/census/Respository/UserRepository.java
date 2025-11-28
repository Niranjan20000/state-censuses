package com.Etech.census.Respository;


import com.Etech.census.entity.AwwWorker;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<AwwWorker, Long> {

Optional<AwwWorker> findByUsername(String username);
}
