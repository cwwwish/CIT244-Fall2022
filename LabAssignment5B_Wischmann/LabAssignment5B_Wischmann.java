import java.util.*;
import java.net.URL;
import java.util.Scanner;
import java.io.IOException;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;

public class LabAssignment5B_Wischmann {

    public static void main(String[] args) throws IOException {
        String[] winners = new String[55];
        String url = "https://blog.ticketcity.com/nfl/super-bowl/super-bowl-champions/";
        Document doc = Jsoup.connect(url).get();
        
        Elements trs = doc.select("table tr");
        int i = 0;
    
for(Element tr : trs) {
        if(tr.select("td:nth-of-type(1)").text().equals("")) {
            continue;
        }
        else {
            final String sbWinner = tr.select("td:nth-of-type(3)").text();
            winners[i] = sbWinner;
        }
        i++;
}
        String userTeam = getTeam(); 
        List<String> list = Arrays.asList(winners);
        
            int count = 0;
            if (list.contains(userTeam)){
                for (int j = 0; j < list.size(); j++) {
                    if(list.get(j).equalsIgnoreCase(userTeam))
                    count++;
                }
                    System.out.println("The " + userTeam + " have won " + count + " super bowl(s). ");
                }
                else {
                    System.out.println("I don't know how to tell you this, but the "
                    + userTeam + " have literally never won a super bowl. :(");
                }
    }
        public static String getTeam() {
            Scanner scan = new Scanner(System.in);
            String team;

            System.out.print("Enter a team name (excluding \"the\") and I will tell you how many "
                    + "times they have won the Super Bowl--> "  );
            team = scan.nextLine();
            return team;
        }    
}
