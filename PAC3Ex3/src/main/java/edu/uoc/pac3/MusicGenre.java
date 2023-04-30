/**
 * Title: Music Management
 * author: Ricard Santiago Raigada García
 * Version: 1.0
 * Date: 30/04/2023
 */

package edu.uoc.pac3;

public enum MusicGenre {
    ALTERNATIVE("Alternative Rock", 'A', 125),
    BLUES("Blues", 'B', 80),
    CLASSICAL("Classical Music", 'C', 60),
    COUNTRY("Country Music", 'Y', 120),
    DISCO("Disco", 'D', 130),
    JAZZ("Jazz", 'J', 100),
    METAL("Metal", 'M', 140),
    POP("Pop Music", 'P', 110),
    ROCK_N_ROLL("Rock & Roll", 'R', 150),
    R_N_B("R & B", 'N', 90),
    OTHER("Other", 'O', 105);


    private final String description;
    private final char code;
    private final int tempo;

    /**
     Constructor of MusicGenre enum.
     @param description brief description of the genre
     @param code character code of the genre
     @param tempo average tempo in bits per minute that characterizes the genre
     */

    MusicGenre(String description, char code, int tempo) {
        this.description = description;
        this.code = code;
        this.tempo = tempo;
    }

    /**
     Get the description of the genre.
     @return brief description of the genre
     */

    public String getDescription() {
        return description;
    }

    /**
     Get the code of the genre.
     @return character code of the genre
     */

    public char getCode() {
        return code;
    }

    /**
     Get the tempo of the genre.
     @return average tempo in bits per minute that characterizes the genre
     */

    public int getTempo() {
        return tempo;
    }

    /**
     Get the MusicGenre corresponding to a code.
     @param code character code of the genre
     @return MusicGenre corresponding to the code, or null if the code does not match any genre
     */

    public static MusicGenre getMusicGenre(char code) {
        for (MusicGenre genre : MusicGenre.values()) {
            if (genre.getCode() == code) {
                return genre;
            }
        }
        return null;
    }

    /**
     Get an array of all MusicGenre values sorted alphabetically by code in ascending order.
     @return array of MusicGenre values sorted by code in ascending order
     */

    public static MusicGenre[] getGenresSortedByCode() {
        MusicGenre[] genres = MusicGenre.values();
        for (int i = 0; i < genres.length - 1; i++) {
            for (int j = i + 1; j < genres.length; j++) {
                if (genres[j].getCode() < genres[i].getCode()) {
                    MusicGenre temp = genres[i];
                    genres[i] = genres[j];
                    genres[j] = temp;
                }
            }
        }
        return genres;
    }

    /**
     Get the MusicGenre with the next highest tempo in ascending order, or itself if it has the highest tempo.
     @return MusicGenre with the next highest tempo, or itself if it has the highest tempo
     */

    public MusicGenre getGenreWithNextHigherTempo() {
        int currentTempo = getTempo();
        MusicGenre nextGenre = null;
        int nextTempo = Integer.MAX_VALUE;
        for (MusicGenre genre : MusicGenre.values()) {
            if (genre != this && genre.getTempo() > currentTempo && genre.getTempo() < nextTempo) {
                nextGenre = genre;
                nextTempo = genre.getTempo();
            }
        }
        return (nextGenre != null) ? nextGenre : this;
    }
}

