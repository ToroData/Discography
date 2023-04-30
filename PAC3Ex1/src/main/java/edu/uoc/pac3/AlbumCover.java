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

    public AlbumCover(String name, String artist, int width, int height) throws Exception {
        setName(name);
        setArtist(artist);
        setWidth(width);
        setHeight(height);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if (name == null || name.trim().isEmpty()) {
            throw new Exception(ERR_UNDEFINED_NAME);
        }
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

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


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) throws Exception {
        if (width <= 0) {
            throw new Exception(ERR_MIN_RESOLUTION);
        }
        if (width * height > MAX_RESOLUTION) {
            throw new Exception(ERR_MAX_RESOLUTION);
        }
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) throws Exception {
        if (height <= 0) {
            throw new Exception(ERR_MIN_RESOLUTION);
        } else if (width * height > MAX_RESOLUTION) {
            throw new Exception(ERR_MAX_RESOLUTION);
        }
        this.height = height;
    }

    public String getAspectRatio() {
        int gcd = gcd(width, height);
        int ratioWidth = width / gcd;
        int ratioHeight = height / gcd;
        return ratioWidth + ":" + ratioHeight;
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

}
