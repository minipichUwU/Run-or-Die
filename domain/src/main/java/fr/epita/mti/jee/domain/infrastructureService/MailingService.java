package fr.epita.mti.jee.domain.infrastructureService;

import fr.epita.mti.jee.domain.model.edition.Edition;

public interface MailingService {
    void notifyCancelling(Edition edition);
}
