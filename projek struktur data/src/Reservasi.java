import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//Deklarasi Kelas
public class Reservasi {

    Queue<String> reservasi = new LinkedList<>();
    //objek Queue dengan tipe data String. dideklarasikan menggunakan implementasi LinkedList.
    //reservasi digunakan untuk menyimpan daftar reservasi.
    int kapasitasReservasi = 5;

    String[] daftarMenu = { "Nasi Goreng", "Mie Goreng", "Ayam Bakar", "Soto Ayam", "Gado-Gado" };

    public void tambahReservasi(Scanner scanner) {
        if (reservasi.size() < kapasitasReservasi) {
            System.out.println();
            System.out.print("Masukkan nama pemesan : ");
            String pemesan = scanner.next();
            System.out.println();
            System.out.println("Daftar Menu :");
            for (int i = 0; i < daftarMenu.length; i++) {
                System.out.println((i + 1) + ". " + daftarMenu[i]);
            }
            System.out.print("Pilihan menu : ");
            int menuPilihan = scanner.nextInt();
            if (menuPilihan >= 1 && menuPilihan <= daftarMenu.length) {
                String menuReservasi = daftarMenu[menuPilihan - 1];
                reservasi.add(pemesan + " - " + menuReservasi);
                System.out.println("Reservasi untuk " + pemesan + " dengan menu " + menuReservasi
                        + " telah ditambahkan.");
            } else {
                System.out.println("Menu tidak valid");
            }
        } else {
            System.out.println("Maaf, kapasitas reservasi sudah penuh.");
        }
    }

    public void batalReservasi(Scanner scanner) {
        if (!reservasi.isEmpty()) {
            //jika reservasi tidak kosong
            System.out.println();
            System.out.print("Masukkan nama pemesan yang ingin dibatalkan : ");
            String pemesan = scanner.next();
            boolean found = false;
            //buat mencari tidak ditemukan
            Queue<String> temp = new LinkedList<>();
            while (!reservasi.isEmpty()) {
                String reservasiCurrent = reservasi.poll();
                if (reservasiCurrent.startsWith(pemesan + " - ")) {
                    System.out.println("Reservasi untuk " + reservasiCurrent + " telah dibatalkan.");
                    found = true;
                } else {
                    temp.add(reservasiCurrent);
                }
            }
            while (!temp.isEmpty()) {
                reservasi.add(temp.poll());
            }
            if (!found) {
                System.out.println("Reservasi untuk " + pemesan + " tidak ditemukan");
            }
        } else {
            System.out.println("Maaf, tidak ada reservasi yang dapat dibatalkan.");
        }
    }

    public void lihatReservasi() {
        if (!reservasi.isEmpty()) {
            System.out.println();
            System.out.println("Daftar reservasi :");
            for (String pemesan : reservasi) {
                System.out.println(pemesan);
            }
        } else {
            System.out.println("Belum ada reservasi");
        }
    }

    public void lihatMenu() {
        System.out.println();
        System.out.println("Daftar Menu :");
        for (String menu : daftarMenu) {
            System.out.println(menu);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Reservasi reservasi = new Reservasi();
        while (true) {
            System.out.println();
            System.out.println("Menu reservasi :");
            System.out.println("1. Tambah reservasi");
            System.out.println("2. Batalkan reservasi");
            System.out.println("3. Lihat daftar reservasi");
            System.out.println("4. Lihat daftar menu");
            System.out.println("5. Keluar dari program");
            System.out.print("Pilihan Anda: ");
            int pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    reservasi.tambahReservasi(scanner);
                    break;
                case 2:
                    reservasi.batalReservasi(scanner);
                    break;
                case 3:
                    reservasi.lihatReservasi();
                    break;
                case 4:
                    reservasi.lihatMenu();
                    break;
                case 5:
                    System.exit(0);
                    System.out.println("Terima kasih telah menggunakan program reservasi makanan");
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
                    break;
            }
        }
    }
}