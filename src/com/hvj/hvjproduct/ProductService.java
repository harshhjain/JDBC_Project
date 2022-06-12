package com.hvj.hvjproduct;

import java.util.List;
import java.util.Scanner;

public class ProductService {
    private Scanner s;
    private DBConnections dbConnections;

    public ProductService(){
        s = new Scanner(System.in);
        dbConnections = new DBConnections();
    }

    //1. Fetch Product Details By Product ID"
    public void getProductDetailsByID(){
        System.out.print("\nEnter the Product ID: ");
        int prodID = s.nextInt();
        ProductDetails productDetails = dbConnections.getProductDetailsByID(prodID);

        if (!productDetails.IsSuccess && productDetails.Exception != null)
            System.out.println("DB call threw an exception: " + productDetails.Exception);

        else if (!productDetails.IsSuccess && productDetails.ErrorMessage != null)
            System.out.println("The productID doesn't exist in DB");

        else
        {
            System.out.println("\n===========================================\nProduct details of '" + prodID + "' below: \n===========================================");
            System.out.println("PrimaryKeyID    :   " + productDetails.PrimaryKeyID);
            System.out.println("ProductID       :   " + productDetails.ProductID);
            System.out.println("ProductName     :   " + productDetails.ProductName);
            System.out.println("Availability    :   " + ((productDetails.ProductStock > 0) ? "In Stock" : "Out Of Stock"));
            System.out.println("ProductType     :   " + productDetails.ProductType);
            System.out.println("==============Thank You====================");
        }
    }

    //2. Find all 'In-Stock' product details"
    public void getAllAvalilableProducts(){

        System.out.println("\nFetching All In-Stock Products from DB");
        List<ProductDetails> productDetails = dbConnections.getAllAvalilableProducts();

        if(productDetails.size() == 0){
            System.out.println("No available products found");
        }
        else if(productDetails.size() == 1 && productDetails.get(0).Exception != null){
            System.out.println("DB call threw an exception: " + productDetails.get(0).Exception);
        }
        else{
            System.out.println("|*|******|*|*****************|*|**************|*|****************|*|*****************|");
            System.out.println("|1|  ID  |2|   PRODUCT_NAME  |3|  PRODUCT_ID  |4|  PRODUCT_TYPE  |5|  PRODUCT_STOCK  |");
            System.out.println("|*|******|*|*****************|*|**************|*|****************|*|*****************|");

            productDetails.forEach((product) -> System.out.println("|1|  " + product.PrimaryKeyID + "  |2| " + product.ProductName + "  |3| " + product.ProductID +
                    "  |4| " + product.ProductType + "  |5| " + (product.ProductStock<1?"Out of stock":product.ProductStock) + "  |"));

            System.out.println("|*|******|*|*****************|*|**************|*|****************|*|*****************|");
        }
        System.out.println("\n==============Thank You====================");

    }

    //3. Find all 'Out-of-Stock' products"
    public void getZeroStockProducts(){
                System.out.println("\nFetching All Out-of-Stock Products from DB");
                List<ProductDetails> productDetails = dbConnections.getZeroStockProducts();

        if(productDetails.size() == 0){
            System.out.println("No available products found");
        }
        else if(productDetails.size() == 1 && productDetails.get(0).Exception != null){
            System.out.println("DB call threw an exception: " + productDetails.get(0).Exception);
        }
        else{
            System.out.println("|*|******|*|*****************|*|**************|*|****************|*|*****************|");
            System.out.println("|1|  ID  |2|   PRODUCT_NAME  |3|  PRODUCT_ID  |4|  PRODUCT_TYPE  |5|  PRODUCT_STOCK  |");
            System.out.println("|*|******|*|*****************|*|**************|*|****************|*|*****************|");

            productDetails.forEach((product) -> System.out.println("|1|  " + product.PrimaryKeyID + "  |2| " + product.ProductName + "  |3| " + product.ProductID +
                    "  |4| " + product.ProductType + "  |5| " + (product.ProductStock<1?"Out of stock":product.ProductStock) + "  |"));

            System.out.println("|*|******|*|*****************|*|**************|*|****************|*|*****************|");
        }
        System.out.println("\n==============Thank You====================");
    }

    //4. Add a NEW product"
    public void addNewProduct(){
        ProductDetails insertNewProductDetailModel = new ProductDetails();
        System.out.println("Enter the Product Details you want to add:-");
        s.nextLine();

        System.out.println("Enter Product Name: ");
        insertNewProductDetailModel.ProductName = s.nextLine();

        System.out.println("Enter Unique Product ID: ");
        insertNewProductDetailModel.ProductID = s.nextInt();

        s.nextLine();
        System.out.println("Enter Product Type: ");
        insertNewProductDetailModel.ProductType = s.nextLine();

        System.out.println("Enter Product Stocks: ");
        insertNewProductDetailModel.ProductStock = s.nextInt();

        ProductDetails productDetails = dbConnections.addNewProduct(insertNewProductDetailModel);

        if (productDetails.IsSuccess)
            System.out.println("Query run ho gai bawa");
        else System.out.println("Laude lag gye :- " + productDetails.Exception);

        System.out.println("\n==============Thank You====================");
    }

    public static HVJProduct listOfAllActions(){
        System.out.println("\nChoose one of the option's below: " +
                "\n    1. Fetch Product Details By Product ID" + //getProductDetailsByID
                "\n    2. Find all 'In-Stock' product details" + //getAllAvalilableProducts
                "\n    3. Find all 'Out-of-Stock' products" +    //getZeroStockProducts
                "\n    4. Add a NEW product" +                   //addNewProduct
                "\n    5. Add stock" +                           //addStock
                "\n    6. Delete a product"+                     //deleteProduct

                "\n\n    9. Exit the program" +
                "\n\n Enter your choice (1 - 10): ");

        return null;
    }

}
