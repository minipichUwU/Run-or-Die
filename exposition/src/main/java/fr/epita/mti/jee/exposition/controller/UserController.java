package fr.epita.mti.jee.exposition.controller;

import fr.epita.mti.jee.application.service.UserService;
import fr.epita.mti.jee.exposition.dto.users.CreateUserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api-user")
@Tag(name = "Utilisateur", description = "gestion des éditions (seulement la création en fin de compte")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    @Operation(summary = "register un utilisateur")
    ResponseEntity<Void> createUser(
        @RequestBody
        CreateUserRequest createUserRequest
    ) {
        userService.createUser(createUserRequest.createDomainUtilisateur());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
