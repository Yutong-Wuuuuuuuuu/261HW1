import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author: Yutong Wu
 * Email: yw6228@rit.edu
 * Description: This is a class for a person for the stable matching problem.
 */
public class Person {
    private String name;
    private ArrayList<Integer> preference;
    private boolean free;
    private int match;
    private int currMatchRank;

    /**
     * Initialize an empty person with no name
     */
    public Person(){
        this.name = "";
        this.preference = new ArrayList<>();
        this.free = true;
        this.match = 0;
        this.currMatchRank = 0;
    }

    /**
     * Set the name of the person
     * @param name name of the person
     */
    public void changeName(String name){
        this.name = name;
    }

    /**
     * Add someone to this person's preference list
     * @param name name of the preference
     */
    public void addPreference(int name){
        this.preference.add(name);
    }

    /**
     * Set this person as unengaged
     */
    public void setFree(){
        this.free = true;
        this.match = 0;
    }

    /**
     * Engage this person with a match, set this person as engaged(not free)
     * @param name Name of the person to be engaged
     */
    public void setMatch(int name){
        this.match = name;
        this.free = false;
    }

    /**
     * Get the name of the match who the person is currently engaged with
     * @return the name of the fiance
     */
    public int getMatch(){
        return this.match;
    }

    /**
     * Check if the person is engaged
     * @return true if the person is unengaged, false otherwise
     */
    public boolean checkFree(){
        return this.free;
    }

    /**
     * Get the name of the person
     * @return The name of the person
     */
    public String getName(){
        return this.name;
    }

    /**
     * Get the list of preference of this person
     * @return the list of preference
     */
    public ArrayList<Integer> getPreference(){
        return this.preference;
    }
}
