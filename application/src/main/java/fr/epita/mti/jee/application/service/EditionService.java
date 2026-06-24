package fr.epita.mti.jee.application.service;

import fr.epita.mti.jee.domain.exception.CancelledEditionException;
import fr.epita.mti.jee.domain.exception.edition.EditionIsNotCancelledException;
import fr.epita.mti.jee.domain.exception.edition.EditionNameExistsException;
import fr.epita.mti.jee.domain.exception.edition.EditionNotFoundException;
import fr.epita.mti.jee.domain.exception.edition.OverlappingEditionException;
import fr.epita.mti.jee.domain.infrastructureService.MailingService;
import fr.epita.mti.jee.domain.model.edition.Edition;
import fr.epita.mti.jee.domain.repository.EditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditionService {
    EditionRepository editionRepository;

    MailingService mailingService;

    @Autowired
    public EditionService(
        EditionRepository editionRepository,
        MailingService mailingService
    ) {
        this.editionRepository = editionRepository;
        this.mailingService    = mailingService;
    }

    public List<Edition> getAllEditions() {
        return editionRepository.getAll();
    }

    public Edition getEdition(Edition edition) {
        return editionRepository
            .getEdition(edition)
            .orElseThrow(EditionNotFoundException::new);
    }

    public void createEdition(Edition edition) {
        if (editionRepository.existsByName(edition.nom())) {
            throw new EditionNameExistsException();
        }

        if (editionRepository.canReserveTimeSlot(edition)) {
            throw new OverlappingEditionException();
        }

        editionRepository.create(Edition.createNew(
            edition.nom(),
            edition.date(),
            edition.plageHoraire(),
            edition.lieu(),
            edition.participants().capaciteMax()
        ));
    }

    public void deleteEdition(Edition edition) {
        Edition editionEntity = editionRepository
            .getEdition(edition)
            .orElseThrow(EditionNotFoundException::new);

        if (!editionEntity.estAnnulee()) {
            throw new EditionIsNotCancelledException();
        }

        editionRepository.delete(editionEntity);
    }

    public void cancelEdition(Edition edition) {
        Edition editionEntity = editionRepository
            .getEdition(edition)
            .orElseThrow(EditionNotFoundException::new);

        if (editionEntity.estAnnulee()) {
            throw new CancelledEditionException("Edition Invalide");
        }

        editionRepository.cancel(editionEntity);
        mailingService.notifyCancelling(editionEntity);
    }
}
