package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Creating Inventory
        HashMap<String, HashMap<String, HashMap<String, Float>>> inventory = new HashMap<>();
        Inventory invt = new Inventory();
        inventory = invt.makeInventory();

        // Validating the cart items
        ValidateCart vc = new ValidateCart();
        vc.validateCartItems(inventory);

        CartProcessing cart = new CartProcessing();
        cart.processCart(vc.invalidItems, vc.prod);

        }
    }





