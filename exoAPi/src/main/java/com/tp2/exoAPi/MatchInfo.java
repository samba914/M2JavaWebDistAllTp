package com.tp2.exoAPi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchInfo {
    String competition;
    int year;
    String round;
    String team1;
    String team2;
    int team1goals;
    int team2goals;
}
