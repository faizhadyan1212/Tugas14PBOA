import java.util.Scanner;
import java.io.*;

public class Transaksi extends Login{
    public static void main(String[] args) {
        Conn.connection();
        Scanner scanner = new Scanner(System.in);

            if (login (scanner)) {
                System.out.println("Otentikasi berhasil. Selamat datang, " + "!");           
            while(true){

            System.out.print("\n====== MENU ======\n"
            + "1. Buat Table\n"
            + "2. Tambah Transaksi\n"
            + "3. Lihat Data Barang\n"
            + "4. Hapus Data Barang\n"
            + "5. Update Data Barang\n"
            + "0. Exit\n"
            + "Pilih[1/2/3/4/5/0] : ");
            
            String pilihan = scanner.nextLine().trim();

            if( pilihan.equalsIgnoreCase("0") ){
                System.out.println("Terimakasih!!");
                break;
            }

            switch (pilihan) {
                case "1" :
                    try {
                    
                    CRUD.createTable();

                } catch (Exception e) {
                    System.err.println("Input tidak valid. Pastikan input sesuai dengan tipe data yang diminta.");
                }       
                    break;
                case "2" :
                    CRUD.tambahData();
                    break;
                case "3" :
                    CRUD.getAllData();
                    break;
                case "4" :
                    CRUD.deleteData();
                    break;
                case "5" :
                    CRUD.updateData();
                    break;
                default:
                    System.out.println("Pilihan salah!!");
                    break;     
            }             
        }
         scanner.close();
           
        } else {
            System.out.println("Nama pengguna atau kata sandi tidak valid. Autentikasi gagal.");
        }
    }
   
}