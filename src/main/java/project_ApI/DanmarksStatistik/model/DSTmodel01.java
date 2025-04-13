package project_ApI.DanmarksStatistik.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DSTmodel01 {

    @Id
    private Long id;
    private String formatCode;
    private int year;
    private int indexPrice;
    private int amount;


    public DSTmodel01 (String formatCode, int year, int indexPrice, int amount){
        this.formatCode = formatCode;
        this.year = year;
        this.indexPrice = indexPrice;
        this.amount = amount;
    }

    public DSTmodel01() {}

    public String getFormatCode() {
        return formatCode;
    }

    public void setFormatCode(String formatCode) {
        this.formatCode = formatCode;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getIndexPrice() {
        return indexPrice;
    }

    public void setIndexPrice(int indexPrice) {
        this.indexPrice = indexPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
