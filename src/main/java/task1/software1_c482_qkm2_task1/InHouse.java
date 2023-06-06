package task1.software1_c482_qkm2_task1;

/**
 * this class will allow part inheritance to be stored for items listed as InHouse.
 */
public class InHouse extends Part {

    private int machineId;

    /**
     * when the part is being saved it will check to see if the part is an InHouse part.<br>
     * If the part is it will save the machineId the part was made on.
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param machineId
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
    this.machineId = machineId;
    }

    /**
     * when this is used it will set the machineId for future use.
     * @param machineId
     */
    public void setMachineId(int machineId){
        this.machineId = machineId;
    }

    /**
     * this will return the set machineId.
     * @return
     */
    public int getMachineId() {
        return machineId;
    }
}
