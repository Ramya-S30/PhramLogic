import java.util.ArrayList;
import java.util.Comparator;

public class ReportsSystem {
    public void showReports(ArrayList<Medicine> inventory, double totalSalesToday) {
        System.out.println("\n--- Reports ---");
        System.out.println("Total Sales Today: ₹" + String.format("%.2f", totalSalesToday));

        System.out.println("\nTop Selling Medicines:");
        inventory.stream().sorted(Comparator.comparing(Medicine::getSold).reversed())
                 .limit(5).forEach(m -> System.out.println(m.getName() + " Sold: " + m.getSold()));

        System.out.println("\nLow Stock Medicines:");
        inventory.stream().filter(m -> m.getQuantity()<5).forEach(m -> 
            System.out.println(m.getName() + " Stock: " + m.getQuantity()));
    }
}
