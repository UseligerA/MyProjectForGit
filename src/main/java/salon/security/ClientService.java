package salon.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import salon.dao.Client;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements UserDetailsService {
    @PersistenceContext
    private EntityManager entityManager;
@Autowired
    ClientRepository clientRepository;
   @Autowired
    RoleRepository roleRepository;
@Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepository.findByUsername(username);
    if(client == null){
        throw new UsernameNotFoundException("Client not found");
    }
    return  client;
    }

    public Client findClientById(Long userId){
        Optional<Client> clientFromDb = clientRepository.findById(userId);
        return  clientFromDb.orElse(new Client());
    }

    public List<Client> allClients(){
        return  clientRepository.findAll();
    }

    public boolean saveClient(Client client){
        /*Client clientFromDb = clientRepository.findByUsername(client.getUsername());

        if(clientFromDb != null){
            return  false;
        }*/

        client.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        System.out.println(client.getUsername());
        System.out.println(client.getPassword());
        System.out.println(client.getRoles());
        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        clientRepository.save(client);
        return true;
    }

    public boolean deleteClient(Long clientId){
        if(clientRepository.findById(clientId).isPresent()){
            clientRepository.deleteById(clientId);
            return true;
        }
        return false;
    }
}
