package fr.epita.mti.jee.run_or_die.exposition.controllers;

import fr.epita.mti.jee.run_or_die.application.services.RunnerService;
import fr.epita.mti.jee.run_or_die.domain.models.coureur.Coureur;
import fr.epita.mti.jee.run_or_die.domain.models.edition.Edition;
import fr.epita.mti.jee.run_or_die.domain.models.utilisateur.Utilisateur;
import fr.epita.mti.jee.run_or_die.exposition.dto.participant.ParticipantEditionDto;
import fr.epita.mti.jee.run_or_die.exposition.dto.participant.ParticipantEditionsResponse;
import fr.epita.mti.jee.run_or_die.exposition.dto.participant.runner.Responses.RunnerEditionsAvailabilityResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api-runner")
@Tag(name = "Coureur", description = "api pour les coureurs")
public class RunnerController {

    private final RunnerService runnerService;

    @Autowired
    public RunnerController(RunnerService runnerService) {
        this.runnerService = runnerService;
    }

    @GetMapping("/editions-availability")
    @Operation(summary = "Visualiser la liste des éditions à venir avec le nombre de places restantes")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RunnerEditionsAvailabilityResponse> getEditionsAvailability() {
        Map<Edition, Long> editionsRemainingCapacity = runnerService.getEditionsRemainingCapacity();

        RunnerEditionsAvailabilityResponse availabilityResponse =
            RunnerEditionsAvailabilityResponse.fromMap(editionsRemainingCapacity);

        return ResponseEntity.status(HttpStatus.OK).body(availabilityResponse);
    }

    @PostMapping("/runner/{editionName}")
    @Operation(summary = "S'inscrire à une édition")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Void> registerRunner(
        @Parameter(description = "nom de l'édition", example = "Run or Die — Édition Octobre 2027")
        @PathVariable
        String editionName, Principal principal
    ) {
        runnerService.registerRunner(
            new Coureur(
                new Utilisateur(principal.getName()),
                new Edition(editionName)
            )
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/runner/registered")
    @Operation(summary = "Visualiser toutes les courses auxquelles il s'est inscrit")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<ParticipantEditionsResponse> getRegisteredRuns(
        Principal principal
    ) {
        Set<Edition> editions = runnerService.getRegisteredRuns(
            new Coureur(
                new Utilisateur(principal.getName()))
        );

        ParticipantEditionsResponse editionsResponse =
            new ParticipantEditionsResponse(editions.stream().map(ParticipantEditionDto::new)
                .toList()
            );

        return ResponseEntity.status(HttpStatus.OK).body(editionsResponse);
    }

    @GetMapping("/runner/registerable")
    @Operation(summary = "Visualiser toutes les courses auxquelles il peut s'inscrire")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<ParticipantEditionsResponse> getRegisterableRuns(
        Principal principal
    ) {
        Set<Edition> editions = runnerService.getRegisterableRuns(
            new Coureur(
                new Utilisateur(principal.getName()))
        );

        ParticipantEditionsResponse editionsResponse =
            new ParticipantEditionsResponse(editions.stream().map(ParticipantEditionDto::new)
                .toList()
            );

        return ResponseEntity.status(HttpStatus.OK).body(editionsResponse);
    }
}
