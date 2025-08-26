import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class BillingSystem {
    private int billCounter = 1001;
    private double totalSalesToday = 0;
    private Scanner sc = new Scanner(System.in);

    public double getTotalSalesToday() { return totalSalesToday; }

    public void generateBill(ArrayList<Medicine> inventory) {
        if(inventory.isEmpty()) { System.out.println("No medicines available for billing."); return; }

        ArrayList<String> billItems = new ArrayList<>();
        double total = 0;

        System.out.print("Customer Name: "); String customer = sc.nextLine();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        while(true) {
            System.out.print("Enter medicine name to buy (or 'done'): ");
            String name = sc.nextLine();
            if(name.equalsIgnoreCase("done")) break;

            Medicine selected = inventory.stream().filter(m -> m.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
            if(selected == null) { System.out.println("Medicine not found."); continue; }

            System.out.print("Enter quantity: "); int qty = sc.nextInt(); sc.nextLine();
            if(qty > selected.getQuantity()) { System.out.println("Not enough stock!"); continue; }

            double cost = qty * selected.getPrice();
            selected.reduceQuantity(qty);
            billItems.add(selected.getName() + " x" + qty + " = ₹" + cost + "\n");
            total += cost;
        }

        double discount = (total > 500) ? total*0.1 : 0;
        total -= discount;
        double gst = total * 0.05;
        total += gst;
        totalSalesToday += total;

        StringBuilder billText = new StringBuilder();
        billText.append("\n===== PharmLogic Bill =====\n");
        billText.append("Bill No: ").append(billCounter++).append("\n");
        billText.append("Customer: ").append(customer).append("\n");
        billText.append("Date & Time: ").append(dtf.format(now)).append("\n");
        billText.append("-----------------------------\n");
        billItems.forEach(billText::append);
        if(discount>0) billText.append("Discount Applied: ₹").append(discount).append("\n");
        billText.append("GST (5%): ₹").append(String.format("%.2f", gst)).append("\n");
        billText.append("-----------------------------\n");
        billText.append("TOTAL: ₹").append(String.format("%.2f", total)).append("\n");
        billText.append("Thank you for shopping! 💊\n");
        System.out.println(billText);

        try(FileWriter fw = new FileWriter("PharmLogic_Bills.txt", true)) {
            fw.write(billText.toString()+"\n");
            System.out.println("Bill saved to PharmLogic_Bills.txt");
        } catch(IOException e) { System.out.println("Error saving bill."); }
    }
}
