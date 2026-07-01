package fr.epita.mti.jee.application.services;

import fr.epita.mti.jee.domain.exceptions.edition.CancelledEditionException;
import fr.epita.mti.jee.domain.exceptions.edition.EditionIsNotCancelledException;
import fr.epita.mti.jee.domain.exceptions.edition.EditionNameExistsException;
import fr.epita.mti.jee.domain.exceptions.edition.EditionNotFoundException;
import fr.epita.mti.jee.domain.exceptions.edition.OverlappingEditionException;
import fr.epita.mti.jee.domain.infrastructure_services.MailingService;
import fr.epita.mti.jee.domain.models.edition.Edition;
import fr.epita.mti.jee.domain.repository.EditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

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

    public Set<Edition> getAllEditions() {
        return editionRepository.getAllEditions();
    }

    public Edition getEdition(Edition editionName) {
        return editionRepository
            .get(editionName)
            .orElseThrow(EditionNotFoundException::new);
    }

    public void createEdition(Edition edition) {
        if (editionRepository.existsEditionWithName(edition.name())) {
            throw new EditionNameExistsException();
        }

        if (editionRepository.doOverlapAnotherEdition(edition)) {
            throw new OverlappingEditionException();
        }

        editionRepository.organize(
            Edition.createNew(
                edition.name(),
                edition.date(),
                edition.timeSlot(),
                edition.location(),
                edition.participants().maxCapacity()
            )
        );
    }

    public void deleteEdition(Edition editionName) {
        Edition edition = editionRepository
            .get(editionName)
            .orElseThrow(EditionNotFoundException::new);

        if (!(edition.isCancelled() || edition.participants().zombies().isEmpty())) {
            throw new EditionIsNotCancelledException();
        }

        editionRepository.delete(edition);
    }

    public void cancelEdition(Edition editionName) {
        Edition edition = editionRepository
            .get(editionName)
            .orElseThrow(EditionNotFoundException::new);

        if (edition.isCancelled()) {
            throw new CancelledEditionException("Edition Invalide");
        }

        editionRepository.cancel(edition);
        mailingService.notifyCancelling(edition);
    }
}
