package fr.epita.mti.jee.run_or_die.exposition.controllers;

import fr.epita.mti.jee.run_or_die.application.services.EditionService;
import fr.epita.mti.jee.run_or_die.domain.models.edition.Edition;
import fr.epita.mti.jee.run_or_die.exposition.dto.edition.requests.CreateEditionRequest;
import fr.epita.mti.jee.run_or_die.exposition.dto.edition.responses.EditionResponse;
import fr.epita.mti.jee.run_or_die.exposition.dto.edition.responses.EditionsResponse;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController()
@RequestMapping("/api-edition")
@Tag(name = "Edition", description = "gestion des éditions")
public class EditionController {

    private final EditionService editionService;

    @Autowired
    public EditionController(EditionService editionService) {
        this.editionService = editionService;
    }

    @GetMapping("/editions")
    @Operation(summary = "visualiser toutes les éditions")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<EditionsResponse> getAllEditions() {
        Set<Edition> editionList = editionService.getAllEditions();

        EditionsResponse editionsResponse = new EditionsResponse(
            editionList
                .stream()
                .map(EditionResponse::new)
                .toList()
        );

        return ResponseEntity.status(HttpStatus.OK).body(editionsResponse);
    }

    @GetMapping("/edition/{editionName}")
    @Operation(summary = "visualiser une édition")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<EditionResponse> getEdition(
        @Parameter(description = "nom de l'édition", example = "Run or Die — Édition Octobre 2027")
        @PathVariable
        String editionName
    ) {
        Edition edition = editionService.getEdition(new Edition(editionName));

        EditionResponse editionResponse = new EditionResponse(edition);

        return ResponseEntity.status(HttpStatus.OK).body(editionResponse);
    }

    @PostMapping("/edition")
    @Operation(summary = "créer une édition")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Void> createEdition(
        @RequestBody
        CreateEditionRequest createEditionRequest
    ) {
        editionService.createEdition(createEditionRequest.createDomainEdition());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/edition/{editionName}")
    @Operation(summary = "annuler une édition")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> cancelEdition(
        @Parameter(description = "nom de l'édition", example = "Run or Die — Édition Octobre 2027")
        @PathVariable
        String editionName
    ) {
        editionService.cancelEdition(new Edition(editionName));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/edition/{editionName}")
    @Operation(summary = "supprimer une édition")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> deleteEdition(
        @Parameter(description = "nom de l'édition", example = "Run or Die — Édition Octobre 2027")
        @PathVariable
        String editionName
    ) {
        editionService.deleteEdition(new Edition(editionName));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
