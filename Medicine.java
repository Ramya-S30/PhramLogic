import java.time.LocalDate;

public class Medicine {
    private String name, manufacturer, category, batchNumber;
    private double price;
    private int quantity;
    private LocalDate expiryDate;
    private int sold = 0; 

    public Medicine(String name, double price, int quantity, LocalDate expiryDate,
                    String batchNumber, String manufacturer, String category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.batchNumber = batchNumber;
        this.manufacturer = manufacturer;
        this.category = category;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public void reduceQuantity(int q) { if(q <= quantity){ quantity -= q; sold += q; } }
    public void addStock(int q) { quantity += q; }
    public void setPrice(double p) { price = p; }
    public String getBatchNumber() { return batchNumber; }
    public String getManufacturer() { return manufacturer; }
    public String getCategory() { return category; }
    public int getSold() { return sold; }

    @Override
    public String toString() {
        return name + " | ₹" + price + " | Stock: " + quantity +
                " | Expiry: " + expiryDate + " | Batch: " + batchNumber +
                " | Manufacturer: " + manufacturer + " | Category: " + category;
    }
}
