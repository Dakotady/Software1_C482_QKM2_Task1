package task1.software1_c482_qkm2_task1;

/**
 * this class will allow part inheritance to be stored for items listed as Outsourced.
 */
public class Outsourced extends Part {

    private String companyName;

    /**
     * when the part is being saved it will check to see if the part is an Outsourced part.<br>
     * If the part is it will save the companyName the part was made from.
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param companyName
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName){
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * when this is used it will set the companyName for future use.
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * this will return the set companyName.
     * @return
     */
    public String getCompanyName() {
        return companyName;
    }
}
