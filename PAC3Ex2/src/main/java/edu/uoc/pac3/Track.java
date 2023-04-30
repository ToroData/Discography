/**
 * Implementation of the track class
 * This class represents a track of an album. It has a name, a composer, and a duration.
 * @author Ricard Santiago Raigada García
 * @version 1.0
 * @date 30/04/2023
 */
package edu.uoc.pac3;

public class Track {

    private String name;
    private String composer;
    private int duration;
    public static final String ERR_MIN_DURATION = "[ERROR] Duration must be greater than 0";


    /**
     * This constructor creates a new instance of the Track class with the specified name, duration, and composer.
     *
     * @param name the name of the track
     * @param duration the duration of the track in seconds
     * @param composer the name of the composer of the track
     * @throws Exception if the duration is less than or equal to 0
     */
    public Track(String name,int duration, String composer) throws Exception {
        setName(name);
        setComposer(composer);
        setDuration(duration);
    }

    /**

     This method returns the name of the track.
     @return the name of the track
     */

    public String getName() {
        return name;
    }

    /**

     This method sets the name of the track to the specified value.
     @param name the new name of the track
     */

    public void setName(String name) {
        this.name = name;
    }

    /**

     This method returns the name of the composer of the track.
     @return the name of the composer of the track
     */

    public String getComposer() {
        return composer;
    }

    /**

     This method sets the name of the composer of the track to the specified value.
     @param composer the new name of the composer of the track
     */

    public void setComposer(String composer) {
        this.composer = composer;
    }

    /**

     This method returns the duration of the track in seconds.
     @return the duration of the track in seconds
     */

    public int getDuration() {
        return duration;
    }

    /**

     This method sets the duration of the track to the specified value.
     @param duration the new duration of the track in seconds
     @throws Exception if the duration is less than or equal to 0
     */

    public void setDuration(int duration) throws Exception {
        if (duration <= 0) {
            throw new Exception(ERR_MIN_DURATION);
        }
        this.duration = duration;
    }

}

