import java.sql.*;

public class Conn {
  private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc";
  private static final String USER = "root";
  private static final String PASS = "MySQL-01qaz";
  private static Connection connect;
  private static Statement statement;
  private static ResultSet resultData;

  // ini adalah method static connection
  public static void connection()
  
  {
    // method untuk melakukan koneksi ke database
    try {
      // registrasi driver yang akan dipakai
      Class.forName(JDBC_DRIVER);

      // buat koneksi ke database
      connect = DriverManager.getConnection(DB_URL, USER, PASS);

    } catch (Exception e) {
      // kalo ada error saat koneksi
      // maka tampilkan errornya
      e.printStackTrace();
    }
  }

  public static String getAllData()
  {
    Conn.connection();

    // isi nilai default dari variabel data
    String data = "Maaf data tidak ada";

    try {

      // buat object statement yang ambil dari koneksi
      statement = connect.createStatement();

      // query select all data from database
      String query = "SELECT noFaktur, customerName, NoHP, alamat,itemName, itemCode, price, quantity FROM transaksi";

      // eksekusi query-nya
      resultData = statement.executeQuery(query);

      // set variabel data jadi null
      data = "";

      // looping pengisian variabel data
      while( resultData.next() ){
      data += "noFaktur : " + resultData.getInt("noFaktur") + "| customerName : " + resultData.getString("customerName")+ 
      "| NoHP : " + resultData.getString("NoHP") + 
      "| alamat : " + resultData.getString("alamat") + 
      "| itemName : " + resultData.getString("itemName") + 
      "| itemCode : " + resultData.getString("itemCode") + 
      "| price : " + resultData.getDouble("price") + 
      "| quantity : " + resultData.getInt("quantity") + "\n";
      }
      // close statement dan connection
      statement.close();
      connect.close();


    } catch (Exception e) {
      e.printStackTrace();
    }

    return data;

  }
public static void createTransaksiTable() {
    Conn.connection();

    try {
        statement = connect.createStatement();

        // Membuat query untuk menciptakan tabel transaksi
        String query = "CREATE TABLE IF NOT EXISTS transaksi (" +
                       "noFaktur VARCHAR(50) PRIMARY KEY," + // Misalnya noFaktur sebagai primary key
                       "customerName VARCHAR(100) NOT NULL," + 
                       "NoHP VARCHAR(15)," + 
                       "alamat VARCHAR(255)," + 
                       "itemName VARCHAR(100) NOT NULL," +
                       "itemCode VARCHAR(50) NOT NULL," +  
                       "price DOUBLE," + 
                       "quantity INT" + 
                       ")";

        // Eksekusi query untuk menciptakan tabel
        statement.execute(query);

    } catch (SQLException e) {
        System.out.println("Error saat menciptakan tabel: " + e.getMessage());
    } finally {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {
            System.out.println("Error saat menutup statement atau koneksi: " + e.getMessage());
        }
    }
}

public static boolean tambahData( String noFaktur, String customerName, String itemName, String itemCode, double price, int quantity, String NoHP, String alamat )
  {
    Conn.connection();
    boolean data = false;

    try {

      statement = connect.createStatement();

      String query = "INSERT INTO transaksi VALUES ('" + noFaktur + "', '" + customerName + "', '" + NoHP + "', '" + alamat + "', '" + itemName + "', '" + itemCode + "', " + price + ", " + quantity + ")";

      if( !statement.execute(query) ){
        data = true;
      }

      // close statement dan koneksi
      statement.close();
      connect.close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }

    return data;
  }

public static boolean deleteData( int noFaktur )
  {
    connection();
    boolean data = false;

    try {
      
      statement = connect.createStatement();

      String query = "DELETE FROM transaksi WHERE noFaktur = " + noFaktur;
      //# String query = "UPDATE transaksi SET isActive = '0' WHERE no_faktur = " + noFaktur;

      if( !statement.execute(query) ){
        data = true;
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return data;
  }

public static boolean updateData( int noFaktur, String itemName, String itemCode, int quantity, int price )
  {

    Conn.connection();
    boolean data = false;

    try {

      statement = connect.createStatement();

      String queryCek = "SELECT * FROM transaksi WHERE noFaktur = " + noFaktur;

      resultData = statement.executeQuery(queryCek);
      // siapin variabel untuk menampung data pada field satu row
      String itemCheck = "";
      String itemCodeCek = "";
      int stokCek = 0, hargaCek = 0;

      while( resultData.next() ){
        itemCheck = resultData.getString("itemName");
        itemCodeCek = resultData.getString("itemCode");
        stokCek = resultData.getInt("quantity");
        hargaCek = resultData.getInt("price");
      }

      // validasi jika yang diisi diconsole kosong
      if( !itemName.equalsIgnoreCase("") ){
        itemCheck = itemName;
      }
      if( !itemCode.equalsIgnoreCase("") ){
        itemCodeCek = itemCode;
      }
      if( quantity != 0 ){
        stokCek = quantity;
      }
      if( price != 0 ){
        hargaCek = price;
      }

      String queryUpdate = "UPDATE transaksi SET itemName = '" + itemCheck + "', itemCode = '" + itemCodeCek + "', quantity = " + stokCek + ", price = " + hargaCek + " WHERE noFaktur = " + noFaktur ;
      
      if( !statement.execute(queryUpdate) ){
        data = true;
      }else{
        data = false;
      }

      // close statement dan close koneksi
      statement.close();
      connect.close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }

    return data;
  }
}