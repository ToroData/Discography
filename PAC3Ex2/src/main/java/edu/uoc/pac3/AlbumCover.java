/**
 * Implementation of the albumCover class
 * This class represents an album cover image with its name, artist, width, and height
 * @author Ricard Santiago Raigada García
 * @version 1.0
 * @date 30/04/2023
 */

package edu.uoc.pac3;

public class AlbumCover {

    private String name;
    private String artist;
    private int width = 560;
    private int height = 480;
    public static final String ERR_UNDEFINED_NAME = "[ERROR] The name cannot be an empty value";
    public static final String ERR_UNKNOWN_ARTIST = "[ERROR] The artist cannot be an empty value";
    public static final String ERR_MIN_RESOLUTION = "[ERROR] Width and height must be positive numbers";
    public static final String ERR_MAX_RESOLUTION = "[ERROR] The image resolution (i.e., width * height) cannot be greater than MAX_RESOLUTION";
    private static final int MAX_RESOLUTION = 1166400;

    /**
     *
     * This constructor creates a new instance of the AlbumCover class with the specified name, artist, width, and height
     * @param name the name of the album cover
     * @param artist the name of the artist who created the album cover
     * @param width the width of the album cover image
     * @param height the height of the album cover image
     * @throws Exception if the name or artist is null or empty, if the resolution is less than or equal to 0,
     *                      or if the resolution exceeds the maximum value
     */

    public AlbumCover(String name, String artist, int width, int height) throws Exception {
        setName(name);
        setArtist(artist);
        setWidth(width);
        setHeight(height);
    }

    /**
     *
     * This method returns the name of the album cover
     * @return the name of the album cover
     */
    public String getName() {
        return name;
    }

    /**
     *
     * This method sets the name of the album cover
     * @param name the name of the album cover
     * @throws Exception if the name is null or empty
     */
    public void setName(String name) throws Exception {
        if (name == null || name.trim().isEmpty()) {
            throw new Exception(ERR_UNDEFINED_NAME);
        }
        this.name = name;
    }

    /**
     *
     * This method returns the name of the artist who created the album cover
     * @return the name of the artist who created the album cover
     */
    public String getArtist() {
        return artist;
    }

    /**
     *
     * This method sets the name of the artist who created the album cover
     * @param artist the name of the artist who created the album cover
     * @throws Exception if the artist is null or empty
     */
    public void setArtist(String artist) throws Exception {
        if (artist == null || artist.trim().isEmpty()) {
            throw new Exception(ERR_UNKNOWN_ARTIST);
        }
        String[] words = artist.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (i > 0) {
                sb.append(" ");
            }
            sb.append(word.substring(0, 1).toUpperCase()).append(word.substring(1).toLowerCase());
        }
        this.artist = sb.toString();
    }

    /**
     *
     * This method returns the width of the album cover image
     * @return the width of the album cover image
     */

    public int getWidth() {
        return width;
    }

    /**
     *
     * Sets the width of the album cover image
     * @param width the width of the image to set
     * @throws Exception if the provided width is less than or equal to 0 or
     * if the resolution (width * height) exceeds the maximum resolution allowed
     */

    public void setWidth(int width) throws Exception {
        if (width <= 0) {
            throw new Exception(ERR_MIN_RESOLUTION);
        }
        if (width * height > MAX_RESOLUTION) {
            throw new Exception(ERR_MAX_RESOLUTION);
        }
        this.width = width;
    }

    /**
     *
     * Returns the height of the album cover image
     * @return the height of the image
     */

    public int getHeight() {
        return height;
    }

    /**
     *
     * Sets the height of the album cover image.
     * @param height the height of the image to set
     * @throws Exception if the provided height is less than or equal to 0 or if the resolution (width * height) exceeds the maximum resolution allowed
     */

    public void setHeight(int height) throws Exception {
        if (height <= 0) {
            throw new Exception(ERR_MIN_RESOLUTION);
        } else if (width * height > MAX_RESOLUTION) {
            throw new Exception(ERR_MAX_RESOLUTION);
        }
        this.height = height;
    }

    /**
     *
     * Returns the aspect ratio of the album cover image as a string in the format of "width:height"
     * @return the aspect ratio of the image
     */

    public String getAspectRatio() {
        int gcd = gcd(width, height);
        int ratioWidth = width / gcd;
        int ratioHeight = height / gcd;
        return ratioWidth + ":" + ratioHeight;
    }

    /**
     *
     * Calculates the greatest common divisor of two integers using the Euclidean algorithm
     * @param a the first integer
     * @param b the second integer
     * @return the greatest common divisor of a and b
     */
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

}
