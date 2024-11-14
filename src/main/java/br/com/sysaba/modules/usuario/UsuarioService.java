package br.com.sysaba.modules.usuario;

import br.com.sysaba.core.commons.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsuarioService implements GenericService<Usuario, UUID> {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Usuario, UUID> getRepository() {
        return repository;
    }

    @Override
    public Usuario findById(UUID id) {
        return GenericService.super.findById(id);
    }

    @Override
    public void delete(UUID id) {
        GenericService.super.delete(id);
    }

    @Override
    public Usuario save(Usuario entity) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        entity.setSenha(passwordEncoder.encode(entity.getSenha()));
        return GenericService.super.save(entity);
    }

    @Override
    public Usuario update(UUID id, Usuario entity) {
        return GenericService.super.update(id, entity);
    }

    @Override
    public Page<Usuario> findAll(Pageable pageable) {
        return GenericService.super.findAll(pageable);
    }

    @Override
    public void beforeSave() {
    }
}
