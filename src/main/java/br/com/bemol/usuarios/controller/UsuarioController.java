package br.com.bemol.usuarios.controller;

import br.com.bemol.usuarios.dto.UsuarioDto;
import br.com.bemol.usuarios.model.Usuario;
import br.com.bemol.usuarios.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listar(){

        List<Usuario> usuarios = usuarioService.lista();

        List<UsuarioDto> resposeDto = usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(resposeDto);
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> cadastrar(@RequestBody UsuarioDto dto){
        Usuario usuario = modelMapper.map(dto, Usuario.class);

        Usuario usuarioSalvo = usuarioService.salvar(usuario);
        UsuarioDto responseDto = modelMapper.map(usuarioSalvo, UsuarioDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
