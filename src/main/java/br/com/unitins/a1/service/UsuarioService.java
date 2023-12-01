package br.com.unitins.a1.service;

import br.com.unitins.a1.dto.*;

public interface UsuarioService<DTO> {
    public Boolean alterarSenha(AlterarSenhaDTO dto, Long id);

    public DTO alterarNome(NomeDTO nome, Long id);

    public DTO alterarCpf(CPFDTO cpf, Long id);

    public DTO alterarEmail(EmailDTO email, Long id);

    public DTO alterarNascimento(NascimentoDTO nascimento, Long id);

    public DTO findById(Long id);

}
