import java.time.LocalDateTime;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // === Création d'un organisateur ===
        Organisateur organisateur = new Organisateur("O1", "Alice", "alice@mail.com");

        // === Création d'une conférence ===
        Conference conf = new Conference(
            "C1", "Conférence Java",
            LocalDateTime.of(2025, 6, 10, 14, 0),
            "Paris", 2,
            "Programmation Orientée Objet"
        );

        conf.ajouterIntervenant(new Intervenant("Dr. Dupont", "Architecture"));
        conf.ajouterIntervenant(new Intervenant("Mme. Leroy", "Design Patterns"));

        // === Création d'un concert ===
        Concert concert = new Concert(
            "C2", "Concert Rock",
            LocalDateTime.of(2025, 6, 12, 20, 0),
            "Lyon", 200,
            "The Rockers",
            "Rock"
        );

        // === Création de participants ===
        Participant p1 = new Participant("P1", "Bob", "bob@mail.com");
        Participant p2 = new Participant("P2", "Charlie", "charlie@mail.com");

        try {
            conf.ajouterParticipant(p1);
            conf.ajouterParticipant(p2);
            concert.ajouterParticipant(p2);
        } catch (CapaciteMaxAtteinteException e) {
            System.out.println("❌ Erreur d'inscription : " + e.getMessage());
        }

        // === Gestionnaire central ===
        GestionEvenements gestion = GestionEvenements.getInstance();
        try {
            gestion.ajouterEvenement(conf);
            gestion.ajouterEvenement(concert);
        } catch (EvenementDejaExistantException e) {
            System.out.println("❌ Erreur d'ajout d'événement : " + e.getMessage());
        }

        organisateur.ajouterEvenement(conf);
        organisateur.ajouterEvenement(concert);

        System.out.println("\n--- Événements organisés ---");
        organisateur.afficherEvenementsOrganises();

        System.out.println("\n--- Détails des événements ---");
        conf.afficherDetails();
        System.out.println();
        concert.afficherDetails();

        System.out.println("\n--- Annulation de la conférence ---");
        conf.annuler();

        // === Sérialisation JSON ===
        System.out.println("\n--- Sauvegarde des événements vers JSON ---");
        EvenementSerializer.sauvegarderVersJson("evenements.json", gestion.getEvenements());

        // === Streams & Lambdas ===

        // 🔹 1. Événements à Paris
        System.out.println("\n--- Événements à Paris ---");
        gestion.getEvenements().values().stream()
            .filter(e -> e.getLieu().equalsIgnoreCase("Paris"))
            .forEach(Evenement::afficherDetails);

        // 🔹 2. Participants triés par nom
        System.out.println("\n--- Participants de la conférence (triés par nom) ---");
        conf.getParticipants().stream()
            .map(Participant::getNom)
            .sorted()
            .forEach(nom -> System.out.println("• " + nom));

        // 🔹 3. Nombre de concerts
        long nbConcerts = gestion.getEvenements().values().stream()
            .filter(e -> e instanceof Concert)
            .count();
        System.out.println("\n🎵 Nombre total de concerts : " + nbConcerts);

        // 🔹 4. Événements avec plus de 100 participants
        System.out.println("\n--- Événements avec plus de 100 participants ---");
        gestion.getEvenements().values().stream()
            .filter(e -> e.getParticipants().size() > 100)
            .forEach(Evenement::afficherDetails);
    }
}





