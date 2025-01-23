/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pengelolafile;

import java.util.Scanner;

/**
 *
 * @author ACER
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Masukkan path folder yang ingin dibuat: ");
                    fileSystem.createFolder(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Masukkan path file yang ingin dibuat: ");
                    String filePath = scanner.nextLine();
                    System.out.print("Masukkan isi file: ");
                    String content = scanner.nextLine();
                    fileSystem.createFile(filePath, content);
                    break;
                case 3:
                    System.out.print("Masukkan path file yang ingin dihapus: ");
                    fileSystem.deleteFile(scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Masukkan path folder yang ingin dihapus: ");
                    fileSystem.deleteFolder(scanner.nextLine());
                    break;
                case 5:
                    fileSystem.displayTreeStructure();
                    break;
                case 6:
                    System.out.print("Masukkan nama file atau folder yang ingin dicari: ");
                    fileSystem.search(scanner.nextLine());
                    break;
                case 7:
                    System.out.println("Terima kasih! Program selesai.");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih kembali.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Buat Folder");
        System.out.println("2. Buat File");
        System.out.println("3. Hapus File");
        System.out.println("4. Hapus Folder");
        System.out.println("5. Tampilkan Struktur Pohon");
        System.out.println("6. Cari File atau Folder");
        System.out.println("7. Keluar");
        System.out.print("Masukkan pilihan: ");
    }
}
