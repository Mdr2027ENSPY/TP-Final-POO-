import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class EvenementSerializer {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // Pour bien gérer les dates avec LocalDateTime
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // pour rendre le JSON lisible
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static void sauvegarderVersJson(String cheminFichier, Map<String, Evenement> evenements) {
        try {
            objectMapper.writeValue(new File(cheminFichier), evenements);
            System.out.println("✅ Événements sauvegardés dans : " + cheminFichier);
        } catch (IOException e) {
            System.out.println("❌ Erreur de sauvegarde JSON : " + e.getMessage());
        }
    }

    public static Map<String, Evenement> chargerDepuisJson(String cheminFichier) {
        try {
            // Désérialisation dans une Map
            return objectMapper.readValue(new File(cheminFichier),
                    objectMapper.getTypeFactory().constructMapType(Map.class, String.class, Evenement.class));
        } catch (IOException e) {
            System.out.println("❌ Erreur de chargement JSON : " + e.getMessage());
            return null;
        }
    }
}
