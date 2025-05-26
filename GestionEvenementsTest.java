import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class GestionEvenementsTest {

    @Test
    public void testAjoutEvenementReussi() throws EvenementDejaExistantException {
        GestionEvenements gestion = GestionEvenements.getInstance();
        Concert concert = new Concert("T1", "Concert Test", LocalDateTime.now(), "Lyon", 100, "Artiste", "Pop");

        boolean resultat = gestion.ajouterEvenement(concert);
        assertTrue(resultat);
        assertTrue(gestion.getEvenements().containsKey("T1"));
    }

    @Test
    public void testAjoutEvenementDoublon() throws EvenementDejaExistantException {
        GestionEvenements gestion = GestionEvenements.getInstance();
        Conference conf = new Conference("T2", "Conf test", LocalDateTime.now(), "Paris", 50, "Java");
        gestion.ajouterEvenement(conf);

        assertThrows(EvenementDejaExistantException.class, () -> {
            gestion.ajouterEvenement(conf); // même ID
        });
    }
}
