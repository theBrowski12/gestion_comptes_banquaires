package cf.bankservice.web;

import cf.bankservice.dto.RequestCompteDto;
import cf.bankservice.dto.ResponseCompteDto;
import cf.bankservice.entities.Compte;
import cf.bankservice.serivce.CompteServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title= "Gestion des comptes bancaire",
                description= "offre tous les methodes pour gérer les comptes",
                version= "1.0.0"
        ),
servers = @Server(
        url = "http://localhost:8081/"
)

)
@RestController
@RequestMapping("/v1/comptes")
public class ApiRestful {
    private CompteServiceImpl compteService;

    public ApiRestful(CompteServiceImpl compteService) {
        this.compteService = compteService;
    }

    @Operation(
            summary = "Ajouter un compte",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseCompteDto.class)

                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description ="bien enregistré",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseCompteDto.class))),
                    @ApiResponse(responseCode = "4xx", description ="erruer cote client"),
                    @ApiResponse(responseCode = "5xx", description ="erruer cote serveur"),


            }
    )
    @PostMapping
    public ResponseEntity<ResponseCompteDto> add(@RequestBody RequestCompteDto requestCompteDto) {
        ResponseCompteDto responseCompteDto = compteService.Add_compte(requestCompteDto);
        return ResponseEntity.ok(responseCompteDto);
    }

    @GetMapping
    public ResponseEntity<List<ResponseCompteDto>> getAll() {
        List<ResponseCompteDto> compteDtos= compteService.GetAllCompte();
        return ResponseEntity.ok(compteDtos);
    }
    @Operation(
            summary = "recuperer un compte par son Id",
            parameters = @Parameter(
                    name = "id",
                    required = true
            ),
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "bien récupérer",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseCompteDto.class))
                    ),
                    @ApiResponse(responseCode = "404",description = "compte pas trouvé ")
            }
    )

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCompteDto> getById(@PathVariable int id) {
        ResponseCompteDto responseCompteDto = compteService.GetCompteById(id);
        return ResponseEntity.ok(responseCompteDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCompteDto> update(@PathVariable int id, @RequestBody RequestCompteDto requestCompteDto) {
        ResponseCompteDto responseCompteDto = compteService.UpdateCompte(id, requestCompteDto);
        return ResponseEntity.ok(responseCompteDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        compteService.DeleteCompteByID(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/crediter/{id}/{montant}")
    ResponseEntity<ResponseCompteDto> crediter(@PathVariable int id, @PathVariable Double montant) {
        ResponseCompteDto responseCompteDto= compteService.crediter(id, montant);
        return ResponseEntity.ok(responseCompteDto);
    }

    @PatchMapping("/debiter/{id}/{montant}")
    ResponseEntity<ResponseCompteDto> debiter(@PathVariable int id, @PathVariable Double montant) {
        ResponseCompteDto responseCompteDto= compteService.Debiter(id, montant);
        return ResponseEntity.ok(responseCompteDto);
    }
}
