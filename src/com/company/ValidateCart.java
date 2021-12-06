package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ValidateCart {

    public ArrayList<Product> prod = new ArrayList<>();

    // Creating Hashmap for all invalid items
    public HashMap<String,Integer> invalidItems = new HashMap<>();

    void validateCartItems(HashMap<String, HashMap<String, HashMap<String, Float>>> inventory )
    {
        HashMap<String, Integer> orders = new HashMap<>();
        Orders ordersMap = new Orders();
        orders = ordersMap.createOrder();

        // Creating Hashmap for limiting the category items
        HashMap<String, Integer> maxCapacity = new HashMap<>();
        maxCapacity.put("Essential",3);
        maxCapacity.put("Luxury",5);
        maxCapacity.put("Misc",6);

        HashMap<String, Integer> orderCap = new HashMap<>();
        HashMap<String,Float> total = new HashMap<>();

        for (Map.Entry<String, Integer> orderList : orders.entrySet())
        {
            String orderItemKey = new String(orderList.getKey());
            if ((inventory.get("Essential")).containsKey(orderItemKey) || (inventory.get("Misc")).containsKey(orderItemKey) || (inventory.get("Luxury")).containsKey(orderItemKey))
            {
                for (Map.Entry<String, HashMap<String, HashMap<String, Float>>> inventoryItems : inventory.entrySet())
                {
                    String inventoryItemKey = new String(orderList.getKey());
                    if (inventoryItems.getValue().containsKey(orderItemKey))
                    {
                        // Condition to check if quantity is not greater than stock and also to check the cap per category
                        if ((inventoryItems.getValue().get(orderItemKey).get("quantity") )>= (float)orderList.getValue() &&  orderList.getValue() <= maxCapacity.get(inventoryItems.getKey()))
                        {
                            if (orderCap.containsKey(inventoryItemKey))
                            {
                                if  (orderCap.get(inventoryItemKey) <= maxCapacity.get(inventoryItemKey))
                                {
                                    orderCap.put(inventoryItemKey,orderCap.get(inventoryItemKey)+1);

                                    //Following Composite Design Pattern
                                    Product p = new Product(orderItemKey);
                                    p.price = (orderList.getValue())*inventoryItems.getValue().get(orderItemKey).get("price");
                                    prod.add(p);
                                }
                                else
                                {
                                    invalidItems.put(orderItemKey, orderList.getValue());
                                }
                            }
                            else
                            {
                                orderCap.put(inventoryItemKey,1);
                                Product p = new Product(orderItemKey);
                                p.price = (orderList.getValue())*inventoryItems.getValue().get(orderItemKey).get("price");
                                prod.add(p);
                            }
                        }
                        else
                        {
                            invalidItems.put(orderItemKey, orderList.getValue());
                        }
                    }
                }
            }
            else
            {
                invalidItems.put(orderItemKey, orderList.getValue());
            }
        }
    }
}
