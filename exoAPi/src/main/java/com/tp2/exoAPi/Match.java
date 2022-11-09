package com.tp2.exoAPi;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class Match {
    public static int goalsMatch(String team, int year){
        // when team is team  1
        String urlTeam1 = "https://jsonmock.hackerrank.com/api/football_matches?year="+year+"&team1="+team+"&page=1";
        //when team is team 2
        String urlTeam2 = "https://jsonmock.hackerrank.com/api/football_matches?year="+year+"&team2="+team+"&page=1";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Page> responseTeam1 = restTemplate.getForEntity(urlTeam1,Page.class);
        ResponseEntity<Page> responseTeam2 = restTemplate.getForEntity(urlTeam2,Page.class);

        Page bodyTeam2  = responseTeam2.getBody();
        Page bodyTeam1  = responseTeam1.getBody();

        int pageTotalNumberTeam1 = bodyTeam1.getTotal();
        int pageTotalNumberTeam2 = bodyTeam2.getTotal();


        List<MatchInfo> matchInfosTeam1 = bodyTeam1.getData();
        List<MatchInfo> matchInfosTeam2 = bodyTeam2.getData();
        int goalTeam=0;
        for(MatchInfo match : matchInfosTeam1){
            goalTeam+=match.getTeam1goals();
        }
        for(MatchInfo match : matchInfosTeam2){
            goalTeam+=match.getTeam2goals();
        }


        for(int i=2;i<=pageTotalNumberTeam1;i++){
            goalTeam += getNbGoalPerPage(i,team,1,year);
        }
        for(int j=2;j<=pageTotalNumberTeam2;j++){
            goalTeam += getNbGoalPerPage(j,team,2,year);
        }

        return goalTeam;
    }

    public static int getNbGoalPerPage(int page,String team,int teamNumber,int year)  //team="team1"  ou "team2"
    {
        String urlTeam = "https://jsonmock.hackerrank.com/api/football_matches?year="+year+"&team"+teamNumber+"="+team+"&page="+page;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Page> responseTeam = restTemplate.getForEntity(urlTeam,Page.class);
        Page bodyTeam  = responseTeam.getBody();
        List<MatchInfo> matchInfosTeam = bodyTeam.getData();
        int goalTeam=0;
        for(MatchInfo match : matchInfosTeam){
            System.out.println(goalTeam);
            if(teamNumber==1){
                goalTeam+=match.getTeam1goals();

            }else{
                goalTeam+=match.getTeam2goals();
            }

        }
        return goalTeam;
    }
    public static void main (String args []){
        String team="Barcelona";
        int year = 2011;
        int nbGoal = Match.goalsMatch(team,year);
        System.out.println(team+" has scored "+ nbGoal+ " goals in "+year);
    }
}
