import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Map;

public class SerializationTest {

    @Test
    public void testSauvegardeEtChargement() {
        GestionEvenements gestion = GestionEvenements.getInstance();
        gestion.getEvenements().clear(); // pour être sûr

        Conference conf = new Conference("S1", "Conf JSON", LocalDateTime.now(), "Dijon", 30, "Test JSON");
        try {
            gestion.ajouterEvenement(conf);
        } catch (EvenementDejaExistantException e) {
            fail("Ajout initial échoué.");
        }

        // Sauvegarde
        EvenementSerializer.sauvegarderVersJson("test.json", gestion.getEvenements());
        assertTrue(new File("test.json").exists());

        // Chargement
        Map<String, Evenement> charges = EvenementSerializer.chargerDepuisJson("test.json");
        assertNotNull(charges);
        assertTrue(charges.containsKey("S1"));
        assertEquals("Conf JSON", charges.get("S1").getNom());
    }
}
