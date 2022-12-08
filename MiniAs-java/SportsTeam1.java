public class SportsTeam1 { 
    
    private String sport;
    private String teamName;
    private int dateFounded;
    
    public SportsTeam1() { 
        
    }
    
    public SportsTeam1(String s, String tn, int d) {
        sport = s;
        teamName = tn;
        dateFounded = d;
    }
    
    public String getSports() {
        return sport;
    }
    
    public void setSport(String sp) {
        sport = sp;
    }
    
    public String getTeamName() {
        return teamName;
    }
    
    public void setTeamName(String tn) {
        teamName = tn;
    }
    
    public int getDateFounded() {
        return dateFounded;
    }
    
    public void setDateFounded(int d) {
        dateFounded = d;
    }
    
    @Override
    public String toString() {
        return "Team Name: " + teamName 
            + "\nSport: " + sport
            + "\nFounded: " + dateFounded;
    }
    
}

// Parent Super Class
