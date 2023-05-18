package task1.software1_c482_qkm2_task1;

public class InHouse {

    private int machineId;

    public InHouse(int ID, String name, double price, int stock, int min, int max, int machineId) {
    this.machineId = machineId;
    }

    public void setMachineId(int machineId){
        this.machineId = machineId;
    }

    public int getMachineId() {
        return machineId;
    }
}
