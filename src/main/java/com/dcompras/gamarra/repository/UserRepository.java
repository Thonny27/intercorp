package com.dcompras.gamarra.repository;

import com.dcompras.gamarra.domain.Usuario;

public interface UserRepository {

    Usuario findByUsername(String username);
}
