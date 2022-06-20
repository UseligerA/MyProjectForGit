package salon.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import salon.dao.Client;


public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByUsername(String username);
//some comments
}
