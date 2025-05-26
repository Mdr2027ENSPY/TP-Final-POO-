import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Conference.class, name = "conference"),
    @JsonSubTypes.Type(value = Concert.class, name = "concert")
})

public abstract class Evenement implements EvenementObservable  {
    protected String id;
    protected String nom;
    protected LocalDateTime date;
    protected String lieu;
    protected int capaciteMax;
    protected List<Participant> participants;
    protected List<ParticipantObserver> observers;

    public Evenement(String id, String nom, LocalDateTime date, String lieu, int capaciteMax) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
        this.capaciteMax = capaciteMax;
        this.participants = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public boolean ajouterParticipant(Participant p) throws CapaciteMaxAtteinteException {
    if (participants.size() >= capaciteMax) {
        throw new CapaciteMaxAtteinteException("Capacité maximale atteinte pour l'événement : " + nom);
    }
    participants.add(p);
    ajouterObserver(p); // s’abonne automatiquement
    System.out.println("Participant ajouté : " + p.getNom());
    return true;
}


     
    public void annuler() {
        System.out.println("⚠️ L'événement \"" + nom + "\" est annulé.");
        notifierObservers("L'événement \"" + nom + "\" a été annulé.");
    }
    
    public void afficherDetails() {
        System.out.println("Événement : " + nom);
        System.out.println("Lieu : " + lieu);
        System.out.println("Date : " + date);
        System.out.println("Capacité max : " + capaciteMax);
        System.out.println("Participants inscrits : " + participants.size());
    }

        // --- Implémentation de EvenementObservable ---
    @Override
    public void ajouterObserver(ParticipantObserver observer) {
        observers.add(observer);
    }

    @Override
    public void retirerObserver(ParticipantObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifierObservers(String message) {
        for (ParticipantObserver o : observers) {
            o.reagirNotification(message);
        }
    }

    // Getters (pour d'autres classes comme GestionEvenements)
    public String getId() { return id; }
    public String getNom() { return nom; }
    public LocalDateTime getDate() { return date; }
    public String getLieu() { return lieu; }
    public int getCapaciteMax() { return capaciteMax; }
    public List<Participant> getParticipants() { return participants; }
}
