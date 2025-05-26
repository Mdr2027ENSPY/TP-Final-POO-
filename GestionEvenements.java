import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionEvenements {
    private static GestionEvenements instance;
    private final Map<String, Evenement> evenements;

    private GestionEvenements() {
        evenements = new HashMap<>();
    }

    public static GestionEvenements getInstance() {
        if (instance == null) {
            instance = new GestionEvenements();
        }
        return instance;
    }

    public boolean ajouterEvenement(Evenement evenement) throws EvenementDejaExistantException {
    if (evenements.containsKey(evenement.getId())) {
        throw new EvenementDejaExistantException("Événement déjà existant avec l'ID : " + evenement.getId());
    }
    evenements.put(evenement.getId(), evenement);
    System.out.println("Événement ajouté : " + evenement.getNom());
    return true;
}

    public boolean supprimerEvenement(String id) {
        if (!evenements.containsKey(id)) {
            System.out.println("Aucun événement trouvé avec l'ID : " + id);
            return false;
        }
        evenements.remove(id);
        System.out.println("Événement supprimé avec l'ID : " + id);
        return true;
    }

    public List<Evenement> rechercherEvenement(String critere) {
        List<Evenement> resultats = new ArrayList<>();
        for (Evenement e : evenements.values()) {
            if (e.getNom().toLowerCase().contains(critere.toLowerCase())) {
                resultats.add(e);
            }
        }
        return resultats;
    }

    public Map<String, Evenement> getEvenements() {
        return evenements;
    }
}

