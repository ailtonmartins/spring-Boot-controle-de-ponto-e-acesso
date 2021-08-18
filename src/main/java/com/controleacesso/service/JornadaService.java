package com.controleacesso.service;

import com.controleacesso.controller.dto.JornadaTrabalhoDto;
import com.controleacesso.controller.form.AtualizacaoJornadaTrabalhoForm;
import com.controleacesso.controller.form.JornadaTrabalhoForm;
import com.controleacesso.model.JornadaTrabalho;
import com.controleacesso.repository.JornadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JornadaService {
    @Autowired
    private JornadaRepository jornadaRepository;

    public ResponseEntity<JornadaTrabalhoDto> saveJornada(JornadaTrabalhoForm jornadaTrabalhoForm) {
        JornadaTrabalho jornadaTrabalho = new JornadaTrabalho(jornadaTrabalhoForm.getDescricao());
        this.jornadaRepository.save(jornadaTrabalho);

        return ResponseEntity.ok(new JornadaTrabalhoDto(jornadaTrabalho));

    }

    public List<JornadaTrabalho> findAll() {
        return this.jornadaRepository.findAll();
    }

    public ResponseEntity<JornadaTrabalhoDto> getById(Long idJornada) {
        Optional<JornadaTrabalho> byId = this.jornadaRepository.findById(idJornada);

        return byId.map(jornadaTrabalho -> ResponseEntity.ok(new JornadaTrabalhoDto(jornadaTrabalho))).orElseGet(() -> ResponseEntity.notFound().build());

        /*if(byId.isPresent()) {
            return ResponseEntity.ok(new JornadaTrabalhoDto(byId.get()));
        } else {
            return ResponseEntity.notFound().build();
        }*/
    }

    public ResponseEntity<JornadaTrabalhoDto> updateJornada( Long idJornada, AtualizacaoJornadaTrabalhoForm form ) {
        Optional<JornadaTrabalho> byId = this.jornadaRepository.findById(idJornada);

        if (byId.isPresent()) {
            JornadaTrabalho jornadaTrabalho = form.update(idJornada, this.jornadaRepository);
            return ResponseEntity.ok(new JornadaTrabalhoDto(jornadaTrabalho));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    public ResponseEntity<JornadaTrabalhoDto> deleteJornada(Long idJornada) {
        Optional<JornadaTrabalho> byId = this.jornadaRepository.findById(idJornada);

        if (byId.isPresent()) {
            this.jornadaRepository.deleteById(idJornada);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}

