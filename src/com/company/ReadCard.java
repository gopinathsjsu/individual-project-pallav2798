package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;


public class ReadCard {
    HashSet<String> cardInfo = new HashSet<>();
    String line = "";
    String splitBy = ",";

    HashSet<String> createCardDB(){

        try {

            BufferedReader br = new BufferedReader(new FileReader("Cards - Sheet1.csv"));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] item = line.split(splitBy);    // use comma as separator

                if ((item[0].equals("CardNumber"))==false){
                    cardInfo.add(item[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cardInfo;
    }
}
