package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class Orders {

    public String line = "";
    public String splitBy = ",";
    public  HashMap<String, Integer>  orders = new HashMap<>();

    HashMap<String, Integer> createOrder()
    {
        // Reading all the card numbers from csv file
        HashSet<String> cardInfo = new HashSet<>();
        ReadCard readCard = new ReadCard();
        cardInfo = readCard.createCardDB();


        try {
            BufferedReader br = new BufferedReader(new FileReader("Input3 - Sheet1.csv"));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] item = line.split(splitBy);    // use comma as separator

                if ((item[0].equals("Item")) == false) {
                    orders.put(item[0], Integer.parseInt(item[1]));


                    if (!cardInfo.contains(item[2])) {
                        try {
                            cardInfo.add(item[2]);
                            FileWriter fw = new FileWriter("./Cards - Sheet1.csv", true);
                            fw.write(item[2] + "\n");
                            fw.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return  orders;
    }
}
