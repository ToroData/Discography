/**
 * Implementation of the album class
 * @author Ricard Santiago Raigada García
 * @version 1.0
 * @date 30/04/2023
 */
package edu.uoc.pac3;

import java.time.Duration;
import java.time.LocalDate;
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

//    private String name;
    private Track[] tracks;
    private static final int MAX_TRACKS = 30;
    public static final String ERR_TRACK_EXISTS = "[ERROR] The track already exists in this album";
    public static final String ERR_TRACK_NOT_EXISTS = "[ERROR] Some of the tracks does not exist in this album";
    public static final String ERR_WRONG_INDEX = "[ERROR] Wrong index";
    private AlbumCover albumCover;

    /**
     * This constructor creates a new instance of the Album class with the specified title, artist, and genre.
     * It also generates a new UUID for the album.
     *
     * @param title the title of the album
     * @param artist the name of the artist
     * @param genre the genre of the album
     * @throws Exception if the artist or genre parameters are invalid.
     */
    public Album(String title, String artist, String genre) throws Exception {
        this.id = UUID.randomUUID();
        this.title = title;
        setArtist(artist);
        setGenre(genre);
        this.tracks = new Track[MAX_TRACKS];
    }

    /**
     * This constructor creates a new instance of the Album class with the specified title, artist, and genre,
     * releaseDate, availableOnline and the price
     *
     * @param title the title of the album
     * @param artist the name of the artist
     * @param genre the genre of the album
     * @param releaseDate the release date of the album
     * @param availableOnline a boolean value indicating if the album is available online
     * @param price the price of the album
     * @throws Exception if there is an error setting any of the properties of the album
     */
    public Album(String title, String artist, String genre, LocalDate releaseDate, boolean availableOnline, double price) throws Exception {
        this(title, artist, genre);
        setReleaseDate(releaseDate);
        setAvailableOnline(availableOnline);
        setPrice(price);
    }

    /**
     * This constructor creates a new instance of the Album class with the specified title, artist, genre,
     * release date, availability online, price, album cover name, album cover artist, album cover width,
     * and album cover height.
     *
     * @param title the title of the album
     * @param artist the name of the artist
     * @param genre the genre of the album
     * @param releaseDate the release date of the album
     * @param availableOnline whether the album is available online or not
     * @param price the price of the album
     * @param albumCoverName the name of the album cover
     * @param albumCoverArtist the name of the album cover artist
     * @param albumCoverWidth the width of the album cover image
     * @param albumCoverHeight the height of the album cover image
     * @throws Exception if there is an error setting any of the properties of the album, such as an invalid date or price
     */
    public Album(String title, String artist, String genre, LocalDate releaseDate, boolean availableOnline, double price,
                 String albumCoverName, String albumCoverArtist, int albumCoverWidth, int albumCoverHeight) throws Exception {
        this(title, artist, genre);
        setReleaseDate(releaseDate);
        setAvailableOnline(availableOnline);
        setPrice(price);
        setAlbumCover(albumCoverName, albumCoverArtist, albumCoverWidth, albumCoverHeight);
    }

    /**
     * Returns an array of all tracks on the album.
     *
     * @return an array of Track objects
     */

    public Track[] getTracks() {
        return tracks;
    }

    /**
     * Returns the track at the specified index on the album.
     *
     * @param index the index of the track to retrieve
     * @return the Track object at the specified index
     * @throws Exception if the index is out of bounds
     */

    public Track getTrack(int index) throws Exception {
        if (index < 0 || index >= MAX_TRACKS) {
            throw new Exception(ERR_WRONG_INDEX);
        }
        return tracks[index];
    }

    /**
     * Checks if a given track exists on the album.
     *
     * @param track the Track object to check for
     * @return true if the track exists on the album, false otherwise
     */

    public boolean isInTheAlbum(Track track) {
        if (track == null || tracks == null) {
            return false;
        }
        for (Track value : tracks) {
            if (track.equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds the index of a given track on the album.
     *
     * @param track the Track object to find the index of
     * @return the index of the track on the album, or -1 if it is not found
     */

    private int findTrackIndex(Track track) {
        if (track == null) {
            return -1;
        }
        for (int i = 0; i < MAX_TRACKS; i++) {
            if (tracks[i] != null && tracks[i].equals(track)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Sets the track at the specified index on the album to the given track.
     *
     * @param index the index of the track to set
     * @param track the Track object to set at the specified index
     * @throws Exception if the index is out of bounds
     */

    private void setTrack(int index, Track track) throws Exception {
        if (index < 0 || index >= MAX_TRACKS) {
            throw new Exception(ERR_WRONG_INDEX);
        }
        tracks[index] = track;
    }

    /**
     * Adds a track to the album.
     *
     * @param track the Track object to add to the album
     * @throws Exception if the track already exists on the album or if the album is full
     */

    public void addTrack(Track track) throws Exception {
        if (track == null) {
            tracks = new Track[MAX_TRACKS];
        }
        if (isInTheAlbum(track)) {
            throw new Exception(ERR_TRACK_EXISTS);
        }
        int index = -1;
        for (int i = 0; i < MAX_TRACKS; i++) {
            if (tracks[i] == null) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new Exception(ERR_WRONG_INDEX);
        }
        setTrack(index, track);
    }

    /**
     * Removes a track from the album.
     *
     * @param track the track to be removed
     */

    public void removeTrack(Track track) {
        if (track == null) {
            return;
        }
        int index = findTrackIndex(track);
        if (index >= 0 && index < MAX_TRACKS) {
            tracks[index] = null;
        }
    }

    /**
     * Removes all tracks from the album.
     */
    public void emptyAlbum() {
        for (int i = 0; i < MAX_TRACKS; i++) {
            tracks[i] = null;
        }
    }

    /**
     * Swaps the positions of two tracks on the album.
     *
     * @param track1 the first track to be swapped
     * @param track2 the second track to be swapped
     * @throws Exception if one or both of the tracks do not exist on the album
     */

    public void swapTracks(Track track1, Track track2) throws Exception {
        if (track1 == null || track2 == null) {
            return;
        }
        int index1 = findTrackIndex(track1);
        int index2 = findTrackIndex(track2);
        if (index1 == -1 || index2 == -1) {
            throw new Exception(ERR_TRACK_NOT_EXISTS);
        }
        tracks[index1] = track2;
        tracks[index2] = track1;
    }

    /**
     * Calculates the total duration of all tracks on the album and returns it as a formatted string.
     * @return the total duration of all tracks on the album as a formatted string (HH:mm:ss)
     */
    public String getTotalDuration() {
        int totalSeconds = 0;
        for (int i = 0; i < MAX_TRACKS; i++) {
            if (tracks[i] != null) {
                totalSeconds += tracks[i].getDuration();
            }
        }
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
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