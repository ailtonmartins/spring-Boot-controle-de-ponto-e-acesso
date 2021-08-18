package com.controleacesso.controller.form;

import com.controleacesso.model.JornadaTrabalho;
import com.controleacesso.repository.JornadaRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AtualizacaoJornadaTrabalhoForm {
    @NotNull @NotEmpty
    public String descricao;

    public JornadaTrabalho update(Long idJornada, JornadaRepository jornadaRepository) {
        JornadaTrabalho jornadaTrabalho = jornadaRepository.getOne(idJornada);
        jornadaTrabalho.setDescricao(this.descricao);
        return jornadaTrabalho;
    }

}
