package com.senescyt.app.service;

import com.senescyt.app.model.Usuario;
import com.senescyt.app.repository.UsuarioRepository;
import com.senescyt.app.service.genericService.GenericService;
import com.senescyt.app.service.genericService.GenericServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class UsuarioService extends GenericServiceImpl<Usuario, Long> implements GenericService<Usuario, Long> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public CrudRepository<Usuario, Long> getDao() {
        return usuarioRepository;
    }

    public Usuario getByUsuarioOrEmail(String nombreOrEmail) {
        return usuarioRepository.findByUsernameOrEmail(nombreOrEmail, nombreOrEmail);
    }

    public List<Usuario> getJefesByRolId(Long id){
        return usuarioRepository.getJefesByRolId(id);
    }

    public List<Object[]> allUsersData(int est) {
        return usuarioRepository.allUsersData(est);
    }

    public Object[] horarioUser(Long id) {
        return usuarioRepository.horarioUser(id);
    }


    public List<Object[]> searchUsersCI(String search, int est) {
        return usuarioRepository.searchUsersCI(search,est);
    }

    public  Usuario findByUsuId(Long id){
        return usuarioRepository.findByUsuId(id);
    }

    public List<Object[]> searchUsersData(String search, int est) {
        return usuarioRepository.searchUsersData(search, est);
    }

    public Usuario findByPersonaCorreo(String email){
        return usuarioRepository.findByUsuCorreo(email);
    }

    public boolean usuarioUnico(String user) {
        int cont = usuarioRepository.usuarioUnico(user.trim());
        System.out.println("user = "+ user+"  count = "+ cont+"\n\n\n\n\n");

        if (cont > 0) {
            return false;
        } else {
            return true;
        }
    }

    public Usuario findByUsuTokenPassword(String token){
        return  usuarioRepository.findByUsuTokenPassword(token);
    }

}
