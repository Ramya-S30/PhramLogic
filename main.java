import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager();
        BillingSystem billingSystem = new BillingSystem();
        ReportsSystem reportsSystem = new ReportsSystem();
        Scanner sc = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\n===== PharmLogic Menu =====");
            System.out.println("1. Add Medicine");
            System.out.println("2. View Medicines");
            System.out.println("3. Search Medicine");
            System.out.println("4. Update Medicine");
            System.out.println("5. Generate Bill");
            System.out.println("6. Reports");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            inventoryManager.removeExpiredMedicines();

            switch (choice) {
                case 1 -> inventoryManager.addMedicine();
                case 2 -> inventoryManager.viewMedicines();
                case 3 -> inventoryManager.searchMedicine();
                case 4 -> inventoryManager.updateMedicine();
                case 5 -> billingSystem.generateBill(inventoryManager.getInventory());
                case 6 -> reportsSystem.showReports(inventoryManager.getInventory(), billingSystem.getTotalSalesToday());
                case 7 -> System.out.println("Exiting PharmLogic... Goodbye!");
                default -> System.out.println("Invalid choice! Try again.");
            }
        } while(choice != 7);
    }
}
