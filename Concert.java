import java.time.LocalDateTime;
import java.util.ArrayList;

public class Concert extends Evenement {
    private  final String artiste;
    private  final String genreMusical;

     public Concert() {
        super(null, null, null, null, 0);
         this.artiste = "";
         this.genreMusical = "";
        
    }

    public Concert(String id, String nom, LocalDateTime date, String lieu, int capaciteMax, String artiste, String genreMusical) {
        super(id, nom, date, lieu, capaciteMax);
        this.artiste = artiste;
        this.genreMusical = genreMusical;
    }

    @Override
    public void afficherDetails() {
        super.afficherDetails();
        System.out.println("Artiste : " + artiste);
        System.out.println("Genre musical : " + genreMusical);
    }

    // Getters
    public String getArtiste() {
        return artiste;
    }

    public String getGenreMusical() {
        return genreMusical;
    }
}
