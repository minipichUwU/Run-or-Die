package fr.epita.mti.jee.exposition.controllers;

import fr.epita.mti.jee.application.services.UserService;
import fr.epita.mti.jee.domain.models.utilisateur.Utilisateur;
import fr.epita.mti.jee.exposition.dto.user.requests.CreateUserRequest;
import fr.epita.mti.jee.exposition.dto.user.responses.UserResponse;
import fr.epita.mti.jee.exposition.dto.user.responses.UsersResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Set;

@RestController()
@RequestMapping("/api-user")
@Tag(name = "Utilisateur", description = "gestion des utilisateurs")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @Operation(summary = "visualiser tous les utilisateurs")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UsersResponse> getAllUsers() {
        Set<Utilisateur> users = userService.getAllUsers();

        UsersResponse usersResponse = new UsersResponse(
            users.stream().map(UserResponse::new).toList()
        );

        return ResponseEntity.status(HttpStatus.OK).body(usersResponse);
    }

    @GetMapping("/user")
    @Operation(summary = "visualiser ses données d'utilisateurs")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserResponse> getSelfUser(Principal principal) {
        Utilisateur user = userService.getUser(new Utilisateur(principal.getName()));

        UserResponse userResponse = new UserResponse(user);

        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @PostMapping("/user")
    @Operation(summary = "créer un utilisateur")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Void> createUser(
        @RequestBody
        CreateUserRequest createUserRequest
    ) {
        userService.createUser(createUserRequest.createDomainUtilisateur());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/user/license")
    @Operation(summary = "vérifier si \"la fédération des survivants\" pense qu'elle vous a licensié")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Boolean> isLicensed(Principal principal) {
        boolean isLicensed = userService.isIllegallyLicensed(new Utilisateur(principal.getName()));
        return ResponseEntity.status(HttpStatus.OK).body(isLicensed);
    }

    @PatchMapping("/user/license/new")
    @Operation(summary = "se faire licencié illégalement (sans attente) auprès de \"la fédération des survivants\"")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> getNewLicense(Principal principal) {
        userService.illegallyLicenseUser(new Utilisateur(principal.getName()));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/user/license/remove")
    @Operation(summary = "appeler \"la fédération des survivants\" pour leur dire la vérité (perdre sa licence) :O")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> removeLicense(Principal principal) {
        userService.illegallyUnlicenseLicense(new Utilisateur(principal.getName()));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
