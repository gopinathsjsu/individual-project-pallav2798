package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartProcessing {

    void processCart(HashMap<String,Integer> invalidItems, ArrayList<Product> prod)
    {

        if (invalidItems.size() != 0){
            //generate text file function

            try {
                FileWriter myWriter = new FileWriter("./Invalid-items.txt");
                myWriter.write("Please correct quantities. \n");
                for (Map.Entry<String, Integer> entry : invalidItems.entrySet()) {
                    myWriter.write(entry.getKey() + " : " + entry.getValue() + "\n");
                }
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Float sum = 0.0f;
            try (FileWriter fout = new FileWriter("./Output.csv"))
            {
                for (Product p : prod) {
                    fout.write(p.description + "," + p.price + "\n");
                    sum += p.price;
                }
                fout.write("Total" + ","+ sum);
                fout.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
