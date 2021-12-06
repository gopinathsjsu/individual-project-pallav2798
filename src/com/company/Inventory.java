package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Inventory {

      String line = "";
      String splitBy = ",";

      HashMap<String, HashMap<String, HashMap<String, Float>>> makeInventory()
      {
          HashMap<String, HashMap<String, HashMap<String, Float>>> inventory = new HashMap<>();

          try {
              //parsing a CSV file into BufferedReader class constructor
              BufferedReader br = new BufferedReader(new FileReader("Dataset - Sheet1.csv"));
              while ((line = br.readLine()) != null)   //returns a Boolean value
              {
                  String[] item = line.split(splitBy);    // use comma as separator

                  if (item[0].equals("Essential") || item[0].equals("Luxury") || item[0].equals("Misc"))
                  {
                      HashMap<String, Float> quantityPrice = new HashMap<String, Float>() {{
                          put("quantity", Float.parseFloat(item[2]));
                          put("price", Float.parseFloat(item[3]));
                      }};
                      HashMap<String, HashMap<String, Float>> itemType = new HashMap<>();
                      itemType.put(item[1], quantityPrice);

                      if (inventory.containsKey(item[0])) {
                          HashMap<String, HashMap<String, Float>> temp;
                          temp = inventory.get(item[0]);
                          temp.put(item[1], quantityPrice);
                      } else {
                          inventory.put(item[0], itemType);
                      }
                  }
              }
          }
          catch (IOException e) {
              e.printStackTrace();
          }
          return inventory;
    }
}
