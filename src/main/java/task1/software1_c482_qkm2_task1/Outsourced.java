package task1.software1_c482_qkm2_task1;

public class Outsourced {

    private String companyName;

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName){
        this.companyName = companyName;
    }


    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }
}
