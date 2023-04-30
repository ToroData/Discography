package edu.uoc.pac3;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

public class Album {
    private static final double MIN_PRICE = 4.99;
    private static final double MAX_PRICE = 29.99;

    private UUID id;
    private String title;
    private String artist;
    private String genre;
    private LocalDate releaseDate = null;
    private boolean availableOnline = false;
    private double price = 14.99;
    public static final String ERR_GENRE = "[ERROR] The genre is not a valid value";
    public static final String ERR_MIN_PRICE = "[ERROR] The album price must be greater than MIN_PRICE";
    public static final String ERR_MAX_PRICE = "[ERROR] The album price must be less than MAX_PRICE (or 80% of MAX_PRICE if the album is available online)";


    private AlbumCover albumCover;

    public Album(String title, String artist, String genre) throws Exception {
        this.id = UUID.randomUUID();
        this.title = title;
        setArtist(artist);
        setGenre(genre);
        this.price = 14.99;
    }

    public Album(String title, String artist, String genre, LocalDate releaseDate, boolean availableOnline, double price) throws Exception {
        this(title, artist, genre);
        setReleaseDate(releaseDate);
        setAvailableOnline(availableOnline);
        setPrice(price);
    }

    public Album(String title, String artist, String genre, LocalDate releaseDate, boolean availableOnline, double price,
                 String albumCoverName, String albumCoverArtist, int albumCoverWidth, int albumCoverHeight) throws Exception {
        this(title, artist, genre, releaseDate, availableOnline, price);
        setAlbumCover(albumCoverName, albumCoverArtist, albumCoverWidth, albumCoverHeight);
    }
    /**
     * Returns the unique Id of the album.
     *
     * @return the unique identifier of the album.
     */
    public UUID getId() {
        return id;
    }
    /**
     * Sets the ID of the album. If the provided UUID is null, a new random UUID is generated.
     *
     * @param id the new unique identifier of the album.
     */
    private void setId(UUID id) {
        this.id = (id == null) ? UUID.randomUUID() : id;
    }
    /**
     * Returns the title of the album.
     *
     * @return the title of the album.
     */
    public String getTitle() {
        return title;
    }
    /**
     * Sets the title of the album.
     *
     * @param title the new title of the album.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    private String formatArtistName(String name) {
        String[] nameParts = name.trim().split("\\s+");
        StringBuilder formattedName = new StringBuilder();
        for (int i = 0; i < nameParts.length; i++) {
            String part = nameParts[i];
            formattedName.append(Character.toUpperCase(part.charAt(0)));
            if (part.length() > 1) {
                formattedName.append(part.substring(1).toLowerCase());
            }
            if (i < nameParts.length - 1) {
                formattedName.append("-");
            }
        }
        return formattedName.toString();
    }
    /**
     * Formats and sets the name of the artist of the album.
     *
     * @param artist the new name of the artist of the album.
     * @throws Exception if the provided artist name is null, empty, or contains invalid characters.
     */
    public void setArtist(String artist) throws Exception {
        if (artist == null || artist.trim().isEmpty()) {
            throw new Exception("[ERROR] The artist cannot be null or empty");
        }
        String formattedArtist = formatArtistName(artist);
        if (formattedArtist.matches("[A-Z][a-z]+(-[A-Z][a-z]+)*")) {
            this.artist = formattedArtist;
        } else {
            throw new Exception("[ERROR] The artist contains invalid characters");
        }
    }
    /**
     * Returns the name of the artist of the album.
     *
     * @return the name of the artist of the album.
     */
    public String getArtist() {
        return artist;
    }
    /**
     * Returns the genre of the album.
     *
     * @return the genre of the album.
     */
    public String getGenre() {
        return genre;
    }
    /**
     * Returns true if the album is available online, false otherwise.
     *
     * @return true if the album is available online, false otherwise.
     */
    public boolean isAvailableOnline() {
        return availableOnline;
    }
    /**
     * Returns the price of the album.
     *
     * @return the price of the album.
     */
    public double getPrice() {
        return Math.round(price * 1000) / 1000.0;
    }

    /**
     * Sets the price of the album. If the album is available online, the price cannot exceed 80% of the maximum price.
     *
     * @param price the new price of the album.
     * @throws Exception if the price is less than the minimum price or exceeds the maximum price.
     */
    public void setPrice(double price) throws Exception {
        if (price < MIN_PRICE) {
            throw new Exception(ERR_MIN_PRICE);
        }
        if (availableOnline && price > MAX_PRICE * 0.8) {
            throw new Exception(ERR_MAX_PRICE);
        }
        if (!availableOnline && price > MAX_PRICE) {
            throw new Exception(ERR_MAX_PRICE);
        }
        this.price = price;
    }
    /**
     * Sets whether the album is available online or not. If the album is available online, the price cannot exceed 80% of the maximum price.
     *
     * @param availableOnline true if the album is available online, false otherwise.
     */
    public void setAvailableOnline(boolean availableOnline) {
        this.availableOnline = availableOnline;
        if (availableOnline && price > MAX_PRICE * 0.8) {
            price = MAX_PRICE * 0.8;
        }
    }
    /**
     * Sets the genre of the album.
     *
     * @param genre the new genre of the album.
     * @throws Exception if the provided genre is not valid.
     */
    public void setGenre(String genre) throws Exception {
        String normalizedGenre = genre.trim().toUpperCase();
        if (!normalizedGenre.equals("ROCK&ROLL") && !normalizedGenre.equals("JAZZ") &&
                !normalizedGenre.equals("POP") && !normalizedGenre.equals("DISCO") &&
                !normalizedGenre.equals("CLASSICAL")) {
            throw new Exception(ERR_GENRE);
        }
        this.genre = normalizedGenre;
    }

    /**
     * Sets the cover of the album.
     *
     * @param name the name of the album cover.
     * @param artist the name of the artist who created the album cover.
     * @param width the width of the album cover in pixels.
     * @param height the height of the album cover in pixels.
     * @throws Exception if the name or artist are null or empty.
     */
    public void setAlbumCover(String name, String artist, int width, int height) throws Exception {
        if (name != null && !name.isEmpty() && artist != null && !artist.isEmpty()) {
            this.albumCover = new AlbumCover(name, artist, width, height);
        } else {
            this.albumCover = null;
        }
    }
    /**
     * Returns the cover of the album.
     *
     * @return the cover of the album.
     */
    public AlbumCover getAlbumCover() {
        return albumCover;
    }
    /**
     * Sets the release date of the album.
     *
     * @param releaseDate the new release date of the album.
     */
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    /**
     * Returns the release date of the album.
     *
     * @return the release date of the album.
     */
    public LocalDate getReleaseDate() {
        return releaseDate;
    }
    /**
     * Returns a formatted string with the release date of the album.
     *
     * @return a formatted string with the release date of the album.
     */
    public String getFormattedReleaseDate() {
        if (releaseDate == null) {
            return null;
        }
        LocalDate now = LocalDate.now();
        LocalDate release = releaseDate;
        String yearString = "";
        if (release.getYear() == now.getYear()) {
            yearString = "(this year)";
        } else if (release.getYear() == now.getYear() - 1) {
            yearString = "(last year)";
        } else if (release.getYear() == now.getYear() + 1) {
            yearString = "(next year)";
        } else if (release.isAfter(now)) {
            int years = release.getYear() - now.getYear();
            if (years == 1) {
                yearString = "(in 1 year)";
            } else {
                yearString = "(in " + years + " years)";
            }
        } else {
            int years = now.getYear() - release.getYear();
            if (years == 1) {
                yearString = "(1 year ago)";
            } else {
                yearString = "(" + years + " years ago)";
            }
        }
        String monthString = release.getMonth().toString().toLowerCase();
        monthString = monthString.substring(0, 1).toUpperCase() + monthString.substring(1);
        int dayOfMonth = release.getDayOfMonth();
        return String.format("Released on %s %d, %d %s", monthString, dayOfMonth, release.getYear(), yearString);
    }

}