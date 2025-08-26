import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class InventoryManager {
    private ArrayList<Medicine> inventory = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public ArrayList<Medicine> getInventory() { return inventory; }

    public void addMedicine() {
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("Price: "); double price = sc.nextDouble();
        System.out.print("Quantity: "); int qty = sc.nextInt(); sc.nextLine();
        System.out.print("Expiry date (YYYY-MM-DD): "); LocalDate expiry = LocalDate.parse(sc.nextLine());
        System.out.print("Batch Number: "); String batch = sc.nextLine();
        System.out.print("Manufacturer: "); String manu = sc.nextLine();
        System.out.print("Category: "); String cat = sc.nextLine();

        inventory.add(new Medicine(name, price, qty, expiry, batch, manu, cat));
        System.out.println("✅ Medicine added successfully!");
    }

    public void viewMedicines() {
        if(inventory.isEmpty()) { System.out.println("No medicines available."); return; }
        System.out.println("\n--- Inventory ---");
        for(Medicine m : inventory) {
            System.out.println(m);
            if(m.getQuantity() < 5) System.out.println("⚠ Low stock alert for " + m.getName());
        }
    }

    public void searchMedicine() {
        System.out.println("Search by: 1.Name 2.Category 3.Manufacturer"); 
        int ch = sc.nextInt(); sc.nextLine();
        switch(ch) {
            case 1 -> { System.out.print("Enter name: "); String n = sc.nextLine();
                inventory.stream().filter(m -> m.getName().equalsIgnoreCase(n)).forEach(System.out::println); }
            case 2 -> { System.out.print("Enter category: "); String c = sc.nextLine();
                inventory.stream().filter(m -> m.getCategory().equalsIgnoreCase(c)).forEach(System.out::println); }
            case 3 -> { System.out.print("Enter manufacturer: "); String mfr = sc.nextLine();
                inventory.stream().filter(m -> m.getManufacturer().equalsIgnoreCase(mfr)).forEach(System.out::println); }
            default -> System.out.println("Invalid choice.");
        }
    }

    public void updateMedicine() {
        System.out.print("Enter medicine name to update: "); String name = sc.nextLine();
        Medicine selected = inventory.stream().filter(m -> m.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        if(selected == null) { System.out.println("Medicine not found!"); return; }

        System.out.println("Update: 1.Price 2.Stock"); int ch = sc.nextInt(); sc.nextLine();
        switch(ch) {
            case 1 -> { System.out.print("New Price: "); double p = sc.nextDouble(); sc.nextLine(); selected.setPrice(p); System.out.println("Price updated."); }
            case 2 -> { System.out.print("Add Stock: "); int q = sc.nextInt(); sc.nextLine(); selected.addStock(q); System.out.println("Stock updated."); }
            default -> System.out.println("Invalid choice.");
        }
    }

    public void removeExpiredMedicines() {
        LocalDate today = LocalDate.now();
        inventory.removeIf(m -> m.getExpiryDate().isBefore(today));
    }
}
