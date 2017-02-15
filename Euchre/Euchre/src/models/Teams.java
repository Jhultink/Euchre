package models;

/**
 * Class that holds team information.
 *  
 * @author Jaredt Hultink, Ryan Jones, Keith Rodgers
 *
 */

public enum Teams {

    BLACK, RED;

    /**
     * Returns the opposite of the current object.
     * 
     * @return Team
     */
    public Teams nextTeam() {
	if (this == Teams.BLACK) {
	    return Teams.RED;
	}
	return BLACK;
    }
}
