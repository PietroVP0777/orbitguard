package br.com.GS.OrbitGuard.controller;

import br.com.GS.OrbitGuard.model.FocosIncendiosDTO;
import br.com.GS.OrbitGuard.service.OrbitGuardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Tag(name = "Focos de Incêndio", description = "Endpoints de monitoramento")
@RestController
@RequestMapping("/focos")
public class OrbitGuardController {
    private final OrbitGuardService service;

    public OrbitGuardController(OrbitGuardService service) {
        this.service = service;
    }


    @Operation(summary = "Lista todos os focos")
    @GetMapping
    public ResponseEntity<List<FocosIncendiosDTO>> listarFocos()
            throws IOException, InterruptedException {

        return ResponseEntity.ok(service.listarFocos());
    }

    @Operation(summary = "Busca focos por país")
    @GetMapping("/{pais}")
    public ResponseEntity<List<FocosIncendiosDTO>> buscarFocosPais(
            @PathVariable String pais) {

        return ResponseEntity.ok(service.buscarFocosPais(pais));
    }
}
