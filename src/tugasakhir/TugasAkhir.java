/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasakhir;

/**
 *
 * @author dell
 */
import java.util.Locale;
import java.text.NumberFormat;
import java.util.Scanner;


public class TugasAkhir {
//public variabel
    public static String DEFAULT_USERNAME = "melina_dyah";
    public static String DEFAULT_PASSWORD = "123456";
    public static int DELUXE_PRICE = 40000;
    public static int IMAX_PRICE = 70000;
    public static int PREMIERE_PRICE = 100000;

    public static String[][] movies = {
            {"Black Panther", "100"},
            {"Gundala", "50"},
            {"Story of Kale", "100"},
            {"Spiderman", "30"}
    };
    public static Scanner scanner = new Scanner(System.in);

    //public static NumberFormat numberFormat= NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

//    Main method
    public static void main(String[] args) {
        mainPage();
    }

    public static void mainPage(){
        System.out.println("------------ Selamat datang ------------");
        System.out.println("1. Login \n2. Keluar");
        System.out.println("Silahkan pilih salah satu angka diatas!");
        int pilihan = scanner.nextInt();

        switch(pilihan){
            case 1 :
                loginPage();
            break;
            case 2 :
                exit();
            break;
            default :{
                inputErrorMsg();
                mainPage();
            }
        }
    }
    
    public static void loginPage(){
        System.out.println("Masukkan Username");
        String username = scanner.next().trim();
        System.out.println("Masukkan Password");
        String password = scanner.next().trim();
        auth(username, password);
    }

    public static void auth(String username, String password){
//            validate the username and password with the default value
        if (username.equals(DEFAULT_USERNAME) && password.equals(DEFAULT_PASSWORD)){
            System.out.println("Berhasil login!");
            mainMenu();
        }else {
            System.out.println("Login gagal!");
        }
    }
    
    public static void mainMenu(){
        System.out.println("------------ Halo," + DEFAULT_USERNAME + "------------");
        System.out.println("1. Pilih Film  \n2. Keluar");
        System.out.println("Silahkan pilih salah satu angka diatas!");
        int pilihan = scanner.nextInt();

        switch (pilihan){
            case 1:
                movieList();
            break;
            case 2:
                exit();
            break;
            default: {
                inputErrorMsg();
                mainMenu();
            }
        }
    }

    public static void movieList(){
        System.out.println("----------------------------------------------");
        System.out.printf("%5s %15s %20s\n", "No", "Film", "Stok tiket");
        System.out.println("----------------------------------------------");
        for (int i = 0; i < movies.length; i++) {
            System.out.format("%5d %20s %15s \n", (i+1),  movies[i][0]  ,Integer.parseInt(movies[i][1]) == 0  ? "Tiket habis" : movies[i][1]);
            System.out.println();
        }
        System.out.println("----------------------------------------------");
        chooseMovie();
    }

    public static void chooseMovie(){
        System.out.println("Pilih salah satu film yang ingin anda tonton diatas!");
        int pilihan = scanner.nextInt();

        if (pilihan > 4){
            System.out.println("Pilih salah satu angka diatas");
            chooseMovie();
        }

        System.out.println("Berapa kursi yang anda pesan?");
        int kursi = scanner.nextInt();

        if (Integer.parseInt(movies[pilihan-1][1]) == 0){
            System.out.println("Ticket habis");
        }

        if (Integer.parseInt(movies[pilihan-1][1]) < kursi){
            System.out.println("Stok tiket tidak mencukupi");
            chooseMovie();
        }

        System.out.print("Pilih studio!");
        System.out.format("\n1. Deluxe: %s \n2. Imax: %s \n3. Premiere: %s", DELUXE_PRICE, IMAX_PRICE, PREMIERE_PRICE);
        System.out.println();
        int studio = scanner.nextInt();
        if (studio > 3 || studio<1){
            System.out.println("Studio tidak ada, Silahkan ulangi pesanan! \n");
            chooseMovie();
        }
        approveTransaction(priceSum(studio, kursi), pilihan-1);
        checkoutTicket(pilihan -1, kursi );
        mainMenu();
    }
    
    public static int priceSum(int studioPilihan, int kursi){
        if (studioPilihan > 3){
            inputErrorMsg();
        }
        switch (studioPilihan){
            case 1:
                return 40000*kursi;
            case 2:
                return 70000*kursi;
            case 3:
                return 100000*kursi;
            default:
                return 0;
        }

    }
    
    public static void approveTransaction(int nominalTotal, int index){
        System.out.println("Apakah anda setuju dengan transaksi dan ingin melanjutkan?");
        System.out.println("1. Ya \n2. Tidak");
        int pilihan = scanner.nextInt();

        switch (pilihan){
            case 1:{
                System.out.println("-------------------------------");
                System.out.println("Film pilihan: "+movies[index][0]);
                System.out.println("-------------------------------");
                System.out.println("Total transaksi: "+ nominalTotal);
                System.out.println("-------------------------------");
                System.out.println("\n\n");
                break;
            }
            case 2:
                mainMenu();
                //break;
            default: {
                inputErrorMsg();
                mainMenu();
            }
        }
    }
    
//checkout item by reduce the ticket quantity
    public static void checkoutTicket(int index, int quantity){
        int ticketQuantity = Integer.parseInt(movies[index][1]);
        int result = ticketQuantity - quantity;
//       example result:  10 - 1 -> 9
        pushNewTicketQuantity(index, result);
    }

//    push new ticket quantity to movies array
    public static void pushNewTicketQuantity(int index, int quantity){
        String qty = String.valueOf(quantity);
        movies[index][1] = qty;
    }

    public static void chosenMovie(int index){
        System.out.println("Film pilihan: "+movies[index][0]);
    }

    public static void exit(){
        System.out.println("-------- Keluar --------");
        System.exit(0);
    }

    public static void inputErrorMsg(){
        System.out.println("Mohon masukkan pilihan angka diatas!");
        System.out.println();
    }


}
