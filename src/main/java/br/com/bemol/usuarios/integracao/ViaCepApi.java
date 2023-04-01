package br.com.bemol.usuarios.integracao;

import br.com.bemol.usuarios.dto.IntegracaoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "viaCepApi", url = "https://viacep.com.br/ws")
public interface ViaCepApi {

    @RequestMapping("{cep}/json/")
    public IntegracaoDto buscaCep(@PathVariable String cep);
}
