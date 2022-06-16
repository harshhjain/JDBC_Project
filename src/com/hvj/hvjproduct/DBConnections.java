package com.hvj.hvjproduct;

//import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.*;

public class DBConnections {

    //1. Fetch Product Details By Product ID
    public ProductDetails getProductDetailsByID (int productID){

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection(AppSettings.ConnectionString);

            PreparedStatement stmt = con.prepareStatement("Select * from PRODUCT_LIST where PRODUCT_ID = ?;");
            stmt.setInt(1, productID);

            //Statement st = con.createStatement();
            //ResultSet rs = st.executeQuery("Select * from PRODUCT_LIST where PRODUCT_ID = 777;");
            //System.out.println("***************STARTING***************");
            //System.out.println("Fetching records from the table...");
            ResultSet rs = stmt.executeQuery();

            if(!rs.next()){
                ProductDetails productDetails = new ProductDetails();
                productDetails.IsSuccess = false;
                productDetails.ErrorMessage = "ProductID does not exist in DB";
                return productDetails;
            }

            ProductDetails productDetails = new ProductDetails();
            productDetails.IsSuccess = true;
            productDetails.PrimaryKeyID = rs.getInt(1);
            productDetails.ProductName = rs.getString(2);
            productDetails.ProductID = rs.getInt(3);
            productDetails.ProductType = rs.getString(4);
            productDetails.ProductStock = rs.getInt(5);

            con.close();

            return productDetails;
        }
        catch (Exception e) {
            //System.out.println(e);
            ProductDetails productDetails = new ProductDetails();
            productDetails.IsSuccess = false;
            productDetails.Exception = e;
            return productDetails;
        }

    }

    // 2. Find all 'In-Stock' product details
    public List<ProductDetails> getAllAvalilableProducts() {
        //System.out.println("Inside getAllAvalilableProdicts");

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(AppSettings.ConnectionString);
            PreparedStatement stmt = con.prepareStatement("SELECT * from PRODUCT_LIST where PRODUCT_STOCK > 0;");

            ResultSet rs = stmt.executeQuery();

            List<ProductDetails> ls = new ArrayList<ProductDetails>();
            while(rs.next()){
                ls.add(new ProductDetails(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5)));
            }
            con.close();

            return ls;
        } catch (Exception e) {
            //System.out.println(e);
            ProductDetails productDetails = new ProductDetails();
            productDetails.IsSuccess = false;
            productDetails.Exception = e;
            List<ProductDetails> ls = new ArrayList<ProductDetails>();
            ls.add(productDetails);
            return ls;
        }
    }

    //3. Find all 'Out-of-Stock' products
    public List<ProductDetails> getZeroStockProducts() {
        //System.out.println("Inside getZeroStockProducts");

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Connection con = DriverManager.getConnection();

//            String UserName = "harsh";
//            String PassWord = "Yash@2020";
//            String DbUrl = "jdbc:sqlserver://product1.database.windows.net:1433;database=HVPROD;user=" + UserName +"@product1;password="+ PassWord + ";encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

            Connection con = DriverManager.getConnection(AppSettings.ConnectionString);
            PreparedStatement stmt = con.prepareStatement("SELECT * from PRODUCT_LIST where PRODUCT_STOCK < 1;");
            //stmt.setInt(1, productID);
            ResultSet rs = stmt.executeQuery();
            List<ProductDetails> lap = new ArrayList<ProductDetails>();
            while (rs.next()){
                lap.add(new ProductDetails(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5)));
            }
            con.close();

            return lap;
        } catch (Exception e) {
            //System.out.println(e);
            ProductDetails productDetails = new ProductDetails();
            productDetails.IsSuccess = false;
            productDetails.Exception = e;
            List<ProductDetails> lap = new ArrayList<ProductDetails>();
            lap.add(productDetails);
            return lap;
        }
    }

    //4. Add a NEW product
    public ProductDetails addNewProduct(ProductDetails insertNewProductDetailModel) {
        //System.out.println("Inside addNewProduct");

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection(AppSettings.ConnectionString);



//            System.out.println("pName  " + pName);
//            System.out.println("pID  " + pID);
//            System.out.println("pType  " + pType);
//            System.out.println("pSock  " + pSock);


            PreparedStatement stmt = con.prepareStatement("INSERT INTO PRODUCT_LIST VALUES (?,?,?,?)");
            stmt.setString(1, insertNewProductDetailModel.ProductName);
            stmt.setInt(2, insertNewProductDetailModel.ProductID);
            stmt.setString(3, insertNewProductDetailModel.ProductType);
            stmt.setInt(4,insertNewProductDetailModel.ProductStock);

//            ResultSet rs =
                    stmt.executeUpdate();

            con.close();

            //System.out.println("Query Updated the details successfully '" + stmt + "'");
            System.out.println("Query Updated the details successfully '" );

            ProductDetails productDetails = new ProductDetails();
            productDetails.IsSuccess = true;

            return productDetails;
        } catch (Exception e) {
            //System.out.println(e);
            ProductDetails productDetails = new ProductDetails();
            productDetails.IsSuccess = false;
            productDetails.Exception = e;
            return productDetails;
        }
    }


//D-1001
//himanshu

}
