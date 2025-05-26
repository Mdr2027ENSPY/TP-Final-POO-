import  org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class ParticipantTest {

    @Test
    public void testInscriptionReussie() throws CapaciteMaxAtteinteException {
        Conference conf = new Conference("C1", "Test Conf", LocalDateTime.now(), "Paris", 2, "POO");
        Participant p = new Participant("P1", "Alice", "alice@mail.com");

        boolean resultat = conf.ajouterParticipant(p);
        assertTrue(resultat);
        assertEquals(1, conf.getParticipants().size());
    }

    @Test
    public void testInscriptionAvecException() {
        Conference conf = new Conference("C2", "Limite", LocalDateTime.now(), "Paris", 1, "Java");
        Participant p1 = new Participant("P1", "Bob", "bob@mail.com");
        Participant p2 = new Participant("P2", "Charlie", "charlie@mail.com");

        try {
            conf.ajouterParticipant(p1);
            conf.ajouterParticipant(p2); // devrait échouer
            fail("Exception attendue non levée.");
        } catch (CapaciteMaxAtteinteException e) {
            assertEquals("Capacité maximale atteinte pour l'événement : Limite", e.getMessage());
        }
    }
}
