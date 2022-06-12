package com.hvj.hvjproduct;

public class ProductDetails {

    public int PrimaryKeyID;
    public String ProductName;
    public int ProductID;
    public String ProductType;
    public int ProductStock;

    public boolean IsSuccess;

    public Exception Exception;

    public String ErrorMessage;

    public ProductDetails(int primaryKeyID, String productName, int productID, String productType, int productStock){
        PrimaryKeyID = primaryKeyID;
        ProductName = productName;
        ProductID = productID;
        ProductStock = productStock;
        ProductType = productType;

    }

    public ProductDetails(){}



}
