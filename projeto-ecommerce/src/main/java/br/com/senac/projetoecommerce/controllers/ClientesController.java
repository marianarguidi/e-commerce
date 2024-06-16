package br.com.senac.projetoecommerce.controllers;

import br.com.senac.projetoecommerce.useCases.clientes.AutenticacaoRequest;
import br.com.senac.projetoecommerce.useCases.clientes.ClientesService;
import br.com.senac.projetoecommerce.useCases.clientes.domains.ClienteRequestDom;
import br.com.senac.projetoecommerce.useCases.clientes.domains.ClienteResponseDom;
import br.com.senac.projetoecommerce.useCases.enderecos.domains.EnderecosRequestDom;
import br.com.senac.projetoecommerce.utils.ResponseUtil;
import br.com.senac.projetoecommerce.utils.SenacExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/clientes")
public class ClientesController {
    @Autowired
    private ClientesService clientesService;

    //carregar todos os clientes
    @GetMapping("/carregar")
    public ResponseEntity<List<ClienteResponseDom>> carregarClientes(){
        try{
            List<ClienteResponseDom> response = clientesService.carregarClientes();

            int status = 200;
            if (response.isEmpty()){
                status=204;
            }

            return ResponseEntity.status(status).body(response);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> criarCliente(@RequestBody ClienteRequestDom cliente){
        try {
            ClienteResponseDom response = clientesService.criarClientes(cliente);
            return ResponseEntity.status(201).body(response);
        } catch (SenacExceptions e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMap(e.getMessages()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(ResponseUtil.responseMap("Erro não Mapeado " + e.getMessage()));
        }
    }

    @PostMapping("/autenticar/")
    public ResponseEntity<?> autenticarCliente( @RequestBody AutenticacaoRequest cliente){
        try {
            ClienteResponseDom response = clientesService.autenticarCliente(cliente.getEmail(), cliente.getSenha());
            return ResponseEntity.ok(response);
        } catch (SenacExceptions e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMap(e.getMessages()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ResponseUtil.responseMap("Erro não Mapeado " + e.getMessage()));
        }
    }

    //carregar apenas o cliente selecionado
    @GetMapping("/carregar/{id}")
    public ResponseEntity<ClienteResponseDom> carregarClienteById(@PathVariable Long id){
        try {
            ClienteResponseDom responseDom = clientesService.carregarClienteById(id);
            if (responseDom != null){
                return ResponseEntity.ok(responseDom);
            }
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    //criar novo cliente
    /*@PostMapping("/cadastrar")
    public ResponseEntity<?> criarCliente(@RequestBody ClienteRequestDom cliente){

        try {
            ClienteResponseDom responseDom = clientesService.criarClientes(cliente);
            return ResponseEntity.status(201).body(responseDom);
        }catch (SenacExceptions es){
            es.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMap(es.getMessages()));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(ResponseUtil.responseMap("Erro não mapeado: " + e.getMessage()));
        }
    }*/

    //atualizar cliente existente
    @Autowired
    private ClientesService clienteService;

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ClienteResponseDom> atualizarCliente(@PathVariable Long id, @RequestBody ClienteRequestDom cliente){
        try {
            ClienteResponseDom clientesResponseDom = clientesService.atualizarCliente(id,cliente);

            if (clientesResponseDom == null){
                return ResponseEntity.badRequest().body(null);
            }
            return ResponseEntity.ok(clientesResponseDom);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    //excluir cliente
    @DeleteMapping("/deletar/{id}")
    public  ResponseEntity<Void> excluirClinte(@PathVariable Long id){
        try{
            clientesService.excluirCliente(id);
            return ResponseEntity.ok(null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }
}
