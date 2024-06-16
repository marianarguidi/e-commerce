package br.com.senac.projetoecommerce.controllers;

import br.com.senac.projetoecommerce.useCases.enderecos.EnderecosService;
import br.com.senac.projetoecommerce.useCases.enderecos.domains.EnderecosRequestDom;
import br.com.senac.projetoecommerce.useCases.enderecos.domains.EnderecosResponseDom;
import br.com.senac.projetoecommerce.useCases.enderecos.implement.EnderecosRepository;
import br.com.senac.projetoecommerce.utils.ResponseUtil;
import br.com.senac.projetoecommerce.utils.SenacExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
    @Controller
    @RequestMapping("/enderecos")
    public class EnderecosController {
        @Autowired
        private EnderecosService enderecosService;
        @Autowired
        private EnderecosRepository enderecosRepository;

    //carregar todos os enderecos de um cliente
        @GetMapping("/carregarEnderecoByCliente/{id}")
        public ResponseEntity<List<EnderecosResponseDom>> carregarEnderecosCliente(@PathVariable Long id) {
            try {
                List<EnderecosResponseDom> responseDoms = (List<EnderecosResponseDom>) enderecosService.carregarEnderecosCliente(id);

                int status = 200;
                if (responseDoms.isEmpty()) {
                    status = 204;
                }
                return ResponseEntity.status(status).body(responseDoms);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

    //criar endereco para um cliente
    @PostMapping("/criarEndereco")
    public ResponseEntity<?> criarEndereco(@RequestBody EnderecosRequestDom endereco) throws SenacExceptions{
        try {
            EnderecosResponseDom enderecosResponseDom = enderecosService.criarEnderecos(endereco);
            return ResponseEntity.status(201).body(enderecosResponseDom);
        }catch (SenacExceptions es){
            es.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMap(es.getMessages()));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(ResponseUtil.responseMap("Erro não mapeado: " + e.getMessage()));
        }
    }

    //atualizar endereço
    @PutMapping("/atualizarEndereco/{idEndereco}")
    public ResponseEntity<EnderecosResponseDom> atualizarEndereco(@PathVariable Long idEndereco,@RequestBody EnderecosRequestDom endereco){

        try {
            EnderecosResponseDom enderecosResponseDom = enderecosService.atualizarEndereco(idEndereco, endereco);

            if (enderecosResponseDom == null){
                return ResponseEntity.badRequest().body(null);
            }
            return ResponseEntity.ok(enderecosResponseDom);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);

        }
    }
    //excluir
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> excluirEndereco(@PathVariable Long id){
        try {
            enderecosService.excluirEndereco(id);
            return ResponseEntity.ok(null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }
}
