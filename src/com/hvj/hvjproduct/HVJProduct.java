package com.hvj.hvjproduct;

import java.util.*;

public class HVJProduct {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.print("Hi There, ");
        ProductService.listOfAllActions();

        ProductService productService = new ProductService();
        DBConnections dbConnections = new DBConnections();

        for (;;) {
            int inputNumber = s.nextInt();

            switch (inputNumber){
                case 1:
                    productService.getProductDetailsByID();
                    break;
                case 2:
                    productService.getAllAvalilableProducts();
                    break;
                case 3:
                    productService.getZeroStockProducts();
                    break;
                case 4:
                    productService.addNewProduct();
                    break;
                case 5:
                    System.out.println("case 5 aayega");
                    break;

                case 9:
                    System.out.println("\n======== Exiting ======== ThankYou ========");
                    break;
                default:
                    System.out.println("\n====Invalid Entry '"+ inputNumber + "'==== \n");

            }
            if (inputNumber == 9) break;

            ProductService.listOfAllActions();
        }
    }
}
