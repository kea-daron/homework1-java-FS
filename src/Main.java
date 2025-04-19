
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    static String[][] stock;
    static String[] history = new String[100];
    static int historyCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n====== Welcome to Java Product Stock Management ======");
            System.out.println("1. Set up Stock");
            System.out.println("2. View Product in Stock");
            System.out.println("3. Insert Product into Stock");
            System.out.println("4. Update Product to Stock");
            System.out.println("5. Delete Product from Stock");
            System.out.println("6. View Insertion History");
            System.out.println("7. Exit Program");
            System.out.print("[ + ] Insert an option: ");
            String userInput = scanner.nextLine();
            choice = Integer.parseInt(userInput);

            if (choice == 1) {
                setupStock(scanner);
            } else if (choice == 2) {
                viewProductInStock();
            } else if (choice == 3) {
                insertProduct(scanner);
            } else if (choice == 4) {
                updateProduct(scanner);
            } else if (choice == 5) {
                deleteProduct(scanner);
            } else if (choice == 6) {
                viewInsertionHistory();
            } else if (choice == 7) {
                System.out.println("[ + ] Exiting the Program.");
                scanner.close();
                return;
            } else {
                System.out.println("[ + ] Invalid choice. Please try again.");
            }
        }
    }

    public static void setupStock(Scanner scanner) {
        System.out.println("\n======= SET UP STOCK =======");
        System.out.print("[ + ] Insert number of stocks: ");
        String userInput = scanner.nextLine();
        int numStocks = Integer.parseInt(userInput);
        stock = new String[numStocks][];

        for (int i = 0; i < numStocks; i++) {
            System.out.print("[ + ] Insert number of catalogues for stock [" + (i + 1) + "]: ");
            userInput = scanner.nextLine();
            int numCatalogues = Integer.parseInt(userInput);
            stock[i] = new String[numCatalogues];
            for (int j = 0; j < numCatalogues; j++) {
                stock[i][j] = "EMPTY";
            }
        }

        System.out.println("- - - - - SET UP STOCK SUCCEEDED - - - - -");
    }

    public static void viewProductInStock() {
        System.out.println("\n======= VIEW PRODUCTS IN STOCK =======");

        if (stock == null || stock.length == 0) {
            System.out.println("[ + ] NOT YET SET UP STOCK");
            return;
        }

        for (int i = 0; i < stock.length; i++) {
            System.out.print("[ + ] Stock [" + (i + 1) + "] => ");
            for (int j = 0; j < stock[i].length; j++) {
                System.out.print("[" + (j + 1) + " - " + stock[i][j] + "] ");
            }
            System.out.println();
        }
    }

    public static void insertProduct(Scanner scanner) {
        System.out.println("\n======= PRODUCT INSERTION MENU =======");

        if (stock == null || stock.length == 0) {
            System.out.println("[ + ] NOT YET SET UP STOCK");
            return;
        }

        boolean isValidSelection = false;
        while (isValidSelection == false) {
            for (int i = 0; i < stock.length; i++) {
                System.out.print("[ + ] Stock [" + (i + 1) + "] => ");
                boolean hasEmptySlot = false;

                for (int j = 0; j < stock[i].length; j++) {
                    if (stock[i][j].equals("EMPTY")) {
                        hasEmptySlot = true;
                    }
                    System.out.print("[" + (j + 1) + " - " + stock[i][j] + "] ");
                }

                System.out.println();
                if (hasEmptySlot == false) {
                    System.out.println("[ + ] Stock [" + (i + 1) + "] is FULL. Only empty slots can be selected.");
                }
            }

            System.out.print("[ + ] Insert stock number: ");
            String input = scanner.nextLine();
            int stockNumber = Integer.parseInt(input) - 1;

            if (stockNumber < 0 || stockNumber >= stock.length) {
                System.out.println("[ + ] Invalid stock number.");
                continue;
            }

            boolean hasEmptySlotInStock = false;
            for (int j = 0; j < stock[stockNumber].length; j++) {
                if (stock[stockNumber][j].equals("EMPTY")) {
                    hasEmptySlotInStock = true;
                    break;
                }
            }

            if (hasEmptySlotInStock == false) {
                continue;
            }

            System.out.print("[ + ] Insert catalogue number: ");
            input = scanner.nextLine();
            int catalogueNumber = Integer.parseInt(input) - 1;

            if (catalogueNumber < 0 || catalogueNumber >= stock[stockNumber].length) {
                System.out.println("[ + ] Invalid catalogue number.");
                continue;
            }

            if (!stock[stockNumber][catalogueNumber].equals("EMPTY")) {
                System.out.println("[ + ] Catalogue number [" + (catalogueNumber + 1) + "] is FULL, please select an empty catalogue.");
                continue;
            }

            System.out.print("[ + ] Insert product name: ");
            String productName = scanner.nextLine();
            stock[stockNumber][catalogueNumber] = productName;

            String timestamp = new SimpleDateFormat("E / MMM / dd HH:mm:ss zzz yyyy").format(new Date());
            history[historyCount] = "[ + ] Inserted at " + timestamp + " Product Name: [" + productName + "]";
            historyCount++;

            System.out.println("\n- - - - - PRODUCT HAS BEEN INSERTED - - - - -");
            isValidSelection = true;
        }
    }

    public static void updateProduct(Scanner scanner) {
        System.out.println("\n======= UPDATE PRODUCT IN STOCK =======");

        if (stock == null || stock.length == 0) {
            System.out.println("[ + ] NOT YET SET UP STOCK");
            return;
        }

        System.out.print("[ + ] Enter stock number: ");
        String userInput = scanner.nextLine();
        int stockNumber = Integer.parseInt(userInput) - 1;

        if (stockNumber < 0 || stockNumber >= stock.length) {
            System.out.println("[ + ] Invalid stock number.");
            return;
        }

        System.out.print("[ + ] Enter catalogue number: ");
        userInput = scanner.nextLine();
        int catalogueNumber = Integer.parseInt(userInput) - 1;

        if (catalogueNumber < 0 || catalogueNumber >= stock[stockNumber].length) {
            System.out.println("[ + ] Invalid catalogue number.");
            return;
        }

        if (stock[stockNumber][catalogueNumber].equals("EMPTY")) {
            System.out.println("[ + ] No product to update. Please insert a product first.");
            return;
        }

        String oldProduct = stock[stockNumber][catalogueNumber];

        System.out.print("[ + ] Enter new product name: ");
        String newProductName = scanner.nextLine();
        stock[stockNumber][catalogueNumber] = newProductName;

        String timestamp = new SimpleDateFormat("E / MMM / dd HH:mm:ss zzz yyyy").format(new Date());
        history[historyCount] = "[ * ] Updated at " + timestamp + " Product update from: [" + oldProduct + "] to: [" + newProductName + "]";
        historyCount++;

        System.out.println("[ + ] Product updated successfully.");
    }

    public static void deleteProduct(Scanner scanner) {
        System.out.println("\n======= DELETE PRODUCT FROM STOCK =======");

        if (stock == null || stock.length == 0) {
            System.out.println("[ + ] NOT YET SET UP STOCK");
            return;
        }

        System.out.print("[ + ] Enter stock number: ");
        String userInput = scanner.nextLine();
        int stockNumber = Integer.parseInt(userInput) - 1;

        if (stockNumber < 0 || stockNumber >= stock.length) {
            System.out.println("[ + ] Invalid stock number.");
            return;
        }

        System.out.print("[ + ] Enter catalogue number: ");
        userInput = scanner.nextLine();
        int catalogueNumber = Integer.parseInt(userInput) - 1;

        if (catalogueNumber < 0 || catalogueNumber >= stock[stockNumber].length) {
            System.out.println("[ + ] Invalid catalogue number.");
            return;
        }

        if (stock[stockNumber][catalogueNumber].equals("EMPTY")) {
            System.out.println("[ + ] Catalogue is empty.");
            return;
        }

        stock[stockNumber][catalogueNumber] = "EMPTY";
        System.out.println("[ + ] Product has been deleted.");
    }

    public static void viewInsertionHistory() {
        System.out.println("\n======= VIEW INSERTION HISTORY =======");

        if (historyCount == 0) {
            System.out.println("[ + ] No products inserted yet.");
        } else {
            for (int i = 0; i < historyCount; i++) {
                System.out.println(history[i]);
            }
        }
    }
}