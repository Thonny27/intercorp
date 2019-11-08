package com.dcompras.gamarra.service;

import com.dcompras.gamarra.domain.Usuario;
import com.dcompras.gamarra.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = userRepository.findByUsername(username);

        if(usuario == null){
            logger.error("No existe usuario '"+usuario+"' en el sistema...");
            throw  new UsernameNotFoundException("No existe usuario '"+usuario+"' en el sistema...");
        }

        List<GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .peek(authotity -> logger.info("Role: "+ authotity.getAuthority()))
                .collect(Collectors.toList());

        return new User(usuario.getUsername(),usuario.getPassword(),usuario.isEnabled(),true,true,true,authorities);
    }
}
