package fr.epita.mti.jee.exposition.controller;

import fr.epita.mti.jee.application.service.EditionService;
import fr.epita.mti.jee.domain.model.edition.Edition;
import fr.epita.mti.jee.exposition.dto.editions.requests.CreateEditionRequest;
import fr.epita.mti.jee.exposition.dto.editions.responses.EditionResponse;
import fr.epita.mti.jee.exposition.dto.editions.responses.EditionsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api-edition")
@Tag(name = "Editions", description = "gestion des éditions")
public class EditionController {

    private final EditionService editionService;

    @Autowired
    public EditionController(EditionService editionService) {
        this.editionService = editionService;
    }

    @GetMapping("/editions")
    @Operation(summary = "visualiser toutes les éditions")
    ResponseEntity<EditionsResponse> getAllEditions() {
        List<Edition> editionList = editionService.getAllEditions();

        EditionsResponse editionsResponse = new EditionsResponse(
            editionList
                .stream()
                .map(EditionResponse::new)
                .toList()
        );

        return ResponseEntity.status(HttpStatus.OK).body(editionsResponse);
    }

    @PostMapping("/edition")
    @Operation(summary = "register une édition")
    ResponseEntity<Void> createEdition(
        @RequestBody
        CreateEditionRequest createEditionRequest
    ) {
        editionService.createEdition(createEditionRequest.createDomainEdition());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/edition/{editionName}")
    @Operation(summary = "visualiser une édition")
    ResponseEntity<EditionResponse> getEdition(
        @Parameter(description = "nom de l'édition", example = "Run or Die — Édition 31 Octobre 2027")
        @PathVariable
        String editionName
    ) {
        Edition edition = editionService.getEdition(new Edition(editionName));

        EditionResponse editionResponse = new EditionResponse(edition);

        return ResponseEntity.status(HttpStatus.OK).body(editionResponse);
    }

    @PatchMapping("/edition/{editionName}")
    @Operation(summary = "annuler une édition")
    ResponseEntity<Void> cancelEdition(
        @Parameter(description = "nom de l'édition", example = "Run or Die — Édition 31 Octobre 2027")
        @PathVariable
        String editionName
    ) {
        editionService.cancelEdition(new Edition(editionName));

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/edition/{editionName}")
    @Operation(summary = "supprimer une édition")
    ResponseEntity<Void> deleteEdition(
        @Parameter(description = "nom de l'édition", example = "Run or Die — Édition 31 Octobre 2027")
        @PathVariable
        String editionName
    ) {
        editionService.deleteEdition(new Edition(editionName));

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
