package cf.bankservice.web;

import cf.bankservice.dto.RequestCompteDto;
import cf.bankservice.dto.ResponseCompteDto;
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
                title = "Gestion des comptes bancaires",
                description = "Offre toutes les méthodes pour gérer les comptes bancaires d’un client.",
                version = "1.0.0"
        ),
        servers = @Server(
                url = "http://localhost:8081/"
        )
)
@RestController
@RequestMapping("/v1/comptes")
public class ApiRestful {

    private final CompteServiceImpl compteService;

    public ApiRestful(CompteServiceImpl compteService) {
        this.compteService = compteService;
    }

    @Operation(
            summary = "Ajouter un compte",
            description = "Permet de créer un nouveau compte bancaire.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestCompteDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Compte enregistré avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseCompteDto.class))),
                    @ApiResponse(responseCode = "400", description = "Erreur côté client"),
                    @ApiResponse(responseCode = "500", description = "Erreur côté serveur")
            }
    )
    @PostMapping
    public ResponseEntity<ResponseCompteDto> add(@RequestBody RequestCompteDto requestCompteDto) {
        ResponseCompteDto responseCompteDto = compteService.Add_compte(requestCompteDto);
        return ResponseEntity.ok(responseCompteDto);
    }

    @Operation(
            summary = "Afficher tous les comptes",
            description = "Retourne la liste de tous les comptes enregistrés dans le système.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Liste des comptes récupérée avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseCompteDto.class))),
                    @ApiResponse(responseCode = "500", description = "Erreur côté serveur")
            }
    )
    @GetMapping
    public ResponseEntity<List<ResponseCompteDto>> getAll() {
        List<ResponseCompteDto> compteDtos = compteService.GetAllCompte();
        return ResponseEntity.ok(compteDtos);
    }

    @Operation(
            summary = "Récupérer un compte par ID",
            description = "Retourne les informations d’un compte spécifique selon son identifiant.",
            parameters = {
                    @Parameter(name = "id", description = "Identifiant du compte", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Compte récupéré avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseCompteDto.class))),
                    @ApiResponse(responseCode = "404", description = "Compte introuvable"),
                    @ApiResponse(responseCode = "500", description = "Erreur côté serveur")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ResponseCompteDto> getById(@PathVariable int id) {
        ResponseCompteDto responseCompteDto = compteService.GetCompteById(id);
        return ResponseEntity.ok(responseCompteDto);
    }

    @Operation(
            summary = "Mettre à jour un compte",
            description = "Modifie les informations d’un compte existant selon son identifiant.",
            parameters = {
                    @Parameter(name = "id", description = "Identifiant du compte à modifier", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestCompteDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Compte mis à jour avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseCompteDto.class))),
                    @ApiResponse(responseCode = "404", description = "Compte introuvable"),
                    @ApiResponse(responseCode = "500", description = "Erreur côté serveur")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ResponseCompteDto> update(@PathVariable int id, @RequestBody RequestCompteDto requestCompteDto) {
        ResponseCompteDto responseCompteDto = compteService.UpdateCompte(id, requestCompteDto);
        return ResponseEntity.ok(responseCompteDto);
    }

    @Operation(
            summary = "Supprimer un compte",
            description = "Supprime un compte de la base de données selon son identifiant.",
            parameters = {
                    @Parameter(name = "id", description = "Identifiant du compte à supprimer", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Compte supprimé avec succès"),
                    @ApiResponse(responseCode = "404", description = "Compte introuvable"),
                    @ApiResponse(responseCode = "500", description = "Erreur côté serveur")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        compteService.DeleteCompteByID(id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Créditer un compte",
            description = "Ajoute un montant au solde d’un compte.",
            parameters = {
                    @Parameter(name = "id", description = "Identifiant du compte", required = true),
                    @Parameter(name = "montant", description = "Montant à créditer", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Compte crédité avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseCompteDto.class))),
                    @ApiResponse(responseCode = "404", description = "Compte introuvable"),
                    @ApiResponse(responseCode = "500", description = "Erreur côté serveur")
            }
    )
    @PatchMapping("/crediter/{id}/{montant}")
    public ResponseEntity<ResponseCompteDto> crediter(@PathVariable int id, @PathVariable Double montant) {
        ResponseCompteDto responseCompteDto = compteService.crediter(id, montant);
        return ResponseEntity.ok(responseCompteDto);
    }

    @Operation(
            summary = "Débiter un compte",
            description = "Retire un montant du solde d’un compte.",
            parameters = {
                    @Parameter(name = "id", description = "Identifiant du compte", required = true),
                    @Parameter(name = "montant", description = "Montant à débiter", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Compte débité avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseCompteDto.class))),
                    @ApiResponse(responseCode = "400", description = "Solde insuffisant"),
                    @ApiResponse(responseCode = "404", description = "Compte introuvable"),
                    @ApiResponse(responseCode = "500", description = "Erreur côté serveur")
            }
    )
    @PatchMapping("/debiter/{id}/{montant}")
    public ResponseEntity<ResponseCompteDto> debiter(@PathVariable int id, @PathVariable Double montant) {
        ResponseCompteDto responseCompteDto = compteService.Debiter(id, montant);
        return ResponseEntity.ok(responseCompteDto);
    }
}
