package fr.epita.mti.jee.exposition.controller;

import fr.epita.mti.jee.application.service.ZombieService;
import fr.epita.mti.jee.domain.model.edition.Edition;
import fr.epita.mti.jee.domain.model.zombie.Zombie;
import fr.epita.mti.jee.exposition.dto.participants.ParticipantEditionDto;
import fr.epita.mti.jee.exposition.dto.participants.participantEditionsResponse;
import fr.epita.mti.jee.exposition.dto.participants.zombies.request.CreateZombieRequest;
import fr.epita.mti.jee.exposition.dto.participants.zombies.response.ZombieDto;
import fr.epita.mti.jee.exposition.dto.participants.zombies.response.ZombieHistogramResponse;
import fr.epita.mti.jee.exposition.dto.participants.zombies.response.ZombiesResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api-zombie")
@Tag(name = "Zombies", description = "gestion des zombies")
public class ZombieController {

    private final ZombieService zombieService;

    @Autowired
    public ZombieController(ZombieService zombieService) {
        this.zombieService = zombieService;
    }

    @PostMapping("/zombie")
    @Operation(summary = "s'affecter en tant que zombie")
    ResponseEntity<Void> createZombie(
        @RequestBody
        CreateZombieRequest createZombieRequest
    ) {
        zombieService.affectZombie(createZombieRequest.createDomainZombie());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/zombie")
    @Operation(summary = "visualiser tous les créneaux auxquels l'utilisateur s'est inscrit")
    ResponseEntity<ZombiesResponse> getRegistrations() {
        String username = Objects
            .requireNonNull(SecurityContextHolder.getContext().getAuthentication())
            .getName();

        List<Zombie> zombies = zombieService.getZombies(new Zombie(username));

        ZombiesResponse zombiesResponse = new ZombiesResponse(zombies
            .stream()
            .map(ZombieDto::new)
            .toList());

        return ResponseEntity.status(HttpStatus.OK).body(zombiesResponse);
    }

    @GetMapping("/registrable-runs/")
    @Operation(summary = "visualiser toutes les courses auxquels l'utilisateur peut s'inscrire")
    ResponseEntity<participantEditionsResponse> getRegistrableRuns() {
        String username = Objects
            .requireNonNull(SecurityContextHolder.getContext().getAuthentication())
            .getName();

        List<Edition> editions = zombieService.getRegisterableRuns(new Zombie(username));

        participantEditionsResponse editionsResponse = new participantEditionsResponse(
            editions.stream().map(ParticipantEditionDto::new).toList());

        return ResponseEntity.status(HttpStatus.OK).body(editionsResponse);
    }

    @GetMapping("/histogram/{editionName}")
    @Operation(summary = "afficher un histogramme (format JSON) heure par heure du nombre de zombies présents à l'événement")
    ResponseEntity<ZombieHistogramResponse> getRegistrableRuns(
        @Parameter(description = "nom de l'édition", example = "Run or Die — Édition 31 Octobre 2027")
        @PathVariable
        String editionName
    ) {
        ZombieHistogramResponse zombieHistogramResponse = ZombieHistogramResponse.fromMap(
            zombieService.getZombiesHistogram(new Edition(editionName)));

        return ResponseEntity.status(HttpStatus.OK).body(zombieHistogramResponse);
    }
}
