package fr.epita.mti.jee.run_or_die.domain.infrastructure_services;

import fr.epita.mti.jee.run_or_die.domain.models.edition.Edition;

public interface MailingService {
    void notifyCancelling(Edition edition);
}
