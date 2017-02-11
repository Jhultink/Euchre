package models;

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
