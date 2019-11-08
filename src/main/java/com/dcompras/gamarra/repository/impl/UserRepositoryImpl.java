package com.dcompras.gamarra.repository.impl;

import com.dcompras.gamarra.domain.Usuario;
import com.dcompras.gamarra.repository.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Override
    public Usuario findByUsername(String username) {
        return null;
    }
}
