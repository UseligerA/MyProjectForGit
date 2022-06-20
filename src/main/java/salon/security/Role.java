package salon.security;

import org.springframework.security.core.GrantedAuthority;
import salon.dao.Client;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_role")
public class Role implements GrantedAuthority {
   @Id
    private Long id;
   private String name;
@Transient
@ManyToMany(mappedBy = "roles")
   private Set<Client> clients;

public Role() {
}
    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Client> getClients() {
        return clients;
    }

    @Override
    public String getAuthority() {
        return "ROLE_USER";
    }
}
