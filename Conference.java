import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Conference extends Evenement {
    private final String theme;
    private  final List<Intervenant> intervenants;
    
     public Conference() {
        super(null, null, null, null, 0);
        this.theme = "";
        this.intervenants = new ArrayList<>();
    }

    public Conference(String id, String nom, LocalDateTime date, String lieu, int capaciteMax, String theme) {
        super(id, nom, date, lieu, capaciteMax);
        this.theme = theme;
        this.intervenants = new ArrayList<>();
    }

    public void ajouterIntervenant(Intervenant i) {
        intervenants.add(i);
    }

    @Override
    public void afficherDetails() {
        super.afficherDetails();
        System.out.println("Thème : " + theme);
        System.out.println("Intervenants :");
        for (Intervenant i : intervenants) {
            System.out.println("  - " + i.getNom() + " (" + i.getSpecialite() + ")");
        }
    }

    // Getters
    public String getTheme() { return theme; }
    public List<Intervenant> getIntervenants() { return intervenants; }
}
