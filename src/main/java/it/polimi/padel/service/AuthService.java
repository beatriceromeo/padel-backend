package it.polimi.padel.service;

import it.polimi.padel.model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private UtenteService userService;

    /**
     * Ritorna l'utente con l'email specificata
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utente user = userService.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Utente non trovato");
        }

        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
