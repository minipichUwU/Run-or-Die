package fr.epita.mti.jee.infrastructure.service;

import fr.epita.mti.jee.domain.infrastructureService.MailingService;
import fr.epita.mti.jee.domain.model.edition.Edition;
import org.springframework.stereotype.Service;

@Service
public class MailingServiceImpl implements MailingService {

    @Override
    public void notifyCancelling(Edition edition) {
        edition.participants().zombies().forEach(zombie -> {
            System.out.println(
                "À l'attention de " + zombie.email() + ",\n" +

                "La zombie runs, édition \"" + edition.nom() + "\", a été annulée.\n" +

                "Vos affectations en tant que Zombie à celle-ci,\n"

                + zombie.plageHoraire() + ", sont caduques."
            ); // mail d'annulation

            System.out.println("Cheh.\n"); // un deuxieme mail.
        });
    }
}
