package fr.epita.mti.jee.exposition.controllers;

import fr.epita.mti.jee.application.services.ZombieService;
import fr.epita.mti.jee.domain.models.edition.Edition;
import fr.epita.mti.jee.domain.models.utilisateur.Utilisateur;
import fr.epita.mti.jee.domain.models.zombie.Zombie;
import fr.epita.mti.jee.exposition.dto.participant.ParticipantEditionDto;
import fr.epita.mti.jee.exposition.dto.participant.ParticipantEditionsResponse;
import fr.epita.mti.jee.exposition.dto.participant.zombie.ZombieDto;
import fr.epita.mti.jee.exposition.dto.participant.zombie.requests.CreateZombieRequest;
import fr.epita.mti.jee.exposition.dto.participant.zombie.responses.ZombieHistogramResponse;
import fr.epita.mti.jee.exposition.dto.participant.zombie.responses.ZombiesResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/api-zombie")
@Tag(name = "Zombie", description = "api pour les zombies")
public class ZombieController {

    private final ZombieService zombieService;

    @Autowired
    public ZombieController(ZombieService zombieService) {
        this.zombieService = zombieService;
    }

    @PostMapping("/zombie")
    @Operation(summary = "s'affecter en tant que zombie")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Void> affectZombie(
        @RequestBody
        CreateZombieRequest createZombieRequest,
        Principal principal
    ) {
        zombieService.affectZombie(createZombieRequest.createDomainZombie(principal.getName()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/zombie")
    @Operation(summary = "visualiser tous les créneaux auxquels l'utilisateur s'est inscrit")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<ZombiesResponse> getRegistrations(Principal principal) {
        Set<Zombie> zombies = zombieService.getAffectedTimeSlots(
            new Zombie(
                new Utilisateur(principal.getName()))
        );

        ZombiesResponse zombiesResponse = new ZombiesResponse(
            zombies.stream()
                .map(ZombieDto::new)
                .toList()
        );

        return ResponseEntity.status(HttpStatus.OK).body(zombiesResponse);
    }

    @GetMapping("/registrable-runs/")
    @Operation(summary = "visualiser toutes les courses auxquels l'utilisateur peut s'inscrire")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<ParticipantEditionsResponse> getRegistrableRuns(Principal principal) {
        Set<Edition> editions = zombieService.getAffectableRuns(
            new Zombie(
                new Utilisateur(principal.getName()))
        );

        ParticipantEditionsResponse editionsResponse =
            new ParticipantEditionsResponse(editions.stream().map(ParticipantEditionDto::new)
                .toList());

        return ResponseEntity.status(HttpStatus.OK).body(editionsResponse);
    }

    @GetMapping("/histogram/{editionName}")
    @Operation(summary = "afficher un histogramme (format JSON) heure par heure du nombre de zombies présents à l'événement")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<ZombieHistogramResponse> getRegistrableRuns(
        @Parameter(description = "nom de l'édition", example = "Run or Die — Édition Octobre 2027")
        @PathVariable
        String editionName
    ) {
        ZombieHistogramResponse zombieHistogramResponse =
            ZombieHistogramResponse.fromMap(
                zombieService.getZombiesHistogram(new Edition(editionName))
            );

        return ResponseEntity.status(HttpStatus.OK).body(zombieHistogramResponse);
    }
}
