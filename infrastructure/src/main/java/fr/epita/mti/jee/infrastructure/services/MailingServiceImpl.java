package fr.epita.mti.jee.infrastructure.services;

import fr.epita.mti.jee.domain.infrastructure_services.MailingService;
import fr.epita.mti.jee.domain.models.edition.Edition;
import org.springframework.stereotype.Service;

@Service
public class MailingServiceImpl implements MailingService {

    @Override
    public void notifyCancelling(Edition edition) {
        edition.participants().zombies().forEach(zombie -> {
            System.out.println(
                "À l'attention de " + zombie.user().username() + ",\n" +

                "La zombie runs, édition \"" + edition.name() + "\", a été annulée.\n" +

                "Vos affectations en tant que Zombie à celle-ci,\n"

                + zombie.timeSlot() + ", sont caduques."
            ); // mail d'annulation

            System.out.println("Cheh.\n"); // un deuxieme mail.
        });

        edition.participants().runners().forEach(runner -> {
            System.out.println(
                "À l'attention de " + runner.user().username() + ",\n" +

                "La zombie runs, édition \"" + edition.name() + "\", a été annulée.\n" +

                "Votre affectation, en tant que Coureur à celle-ci, est caduque."
            ); // mail d'annulation

            System.out.println("Cheh.\n"); // un deuxieme mail.
        });
    }
}
