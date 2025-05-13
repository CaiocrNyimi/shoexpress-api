package br.com.fiap.shoexpress_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fiap.shoexpress_api.repository.UserRepository;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository UserReposiory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return UserReposiory.findByEmail(username).orElseThrow(
            () -> new UsernameNotFoundException("Usuário não encontrado"));
    }
    
}
