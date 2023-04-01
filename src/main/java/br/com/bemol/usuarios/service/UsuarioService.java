package br.com.bemol.usuarios.service;

import br.com.bemol.usuarios.dto.IntegracaoDto;
import br.com.bemol.usuarios.dto.UsuarioDto;
import br.com.bemol.usuarios.entity.UsuarioEntity;
import br.com.bemol.usuarios.integracao.ViaCepApi;
import br.com.bemol.usuarios.model.Usuario;
import br.com.bemol.usuarios.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ViaCepApi viaCepApi;

    public List<Usuario> lista(){
        List<UsuarioEntity> usuariosSalvos = usuarioRepository.findAll();
        return usuariosSalvos
                .stream()
                .map(usuarioEntity -> modelMapper.map(usuarioEntity, Usuario.class))
                .collect(Collectors.toList());
    }


    public Usuario salvar(Usuario usuario) {

        IntegracaoDto integracaoDto = viaCepApi.buscaCep(usuario.getCep());
        this.buildUsuario(usuario, integracaoDto);
        UsuarioEntity usuarioEntity = modelMapper.map(usuario, UsuarioEntity.class);
        UsuarioEntity usuarioSalvo = usuarioRepository.save(usuarioEntity);
        return modelMapper.map(usuarioSalvo, Usuario.class);
    }
    private void buildUsuario(Usuario usuario, IntegracaoDto integracaoDto){
        usuario.setLogradouro(integracaoDto.getLogradouro());
        usuario.setComplemento(integracaoDto.getComplemento());
        usuario.setBairro(integracaoDto.getBairro());
        usuario.setCidade(integracaoDto.getLocalidade());
        usuario.setUf(integracaoDto.getUf());
    }
}
