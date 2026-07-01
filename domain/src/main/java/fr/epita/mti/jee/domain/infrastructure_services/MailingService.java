package fr.epita.mti.jee.domain.infrastructure_services;

import fr.epita.mti.jee.domain.models.edition.Edition;

public interface MailingService {
    void notifyCancelling(Edition edition);
}
