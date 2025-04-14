package project_ApI.DanmarksStatistik.model;


import jakarta.persistence.*;

@Entity
public class DSTmodel01 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String formatCode;
    @Column(name = "year_value")
    private int year;
    private String indexPrice;
    private int amount;


    public DSTmodel01 (String formatCode, int year, String indexPrice, int amount){
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

    public String getIndexPrice() {
        return indexPrice;
    }

    public void setIndexPrice(String indexPrice) {
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
