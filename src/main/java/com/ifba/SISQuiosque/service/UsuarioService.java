package com.ifba.SISQuiosque.service;

import com.ifba.SISQuiosque.dto.UsuarioDTO;

public interface UsuarioService {
    void save(UsuarioDTO usuario);
    void updatePassword(String email, String newPassword);
    boolean existeUsuarioPorEmail(String email);
}