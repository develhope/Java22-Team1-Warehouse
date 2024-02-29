import Devices.DeviceClasses;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class WarehouseTest {

    @Test
    public void testAddDevice() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(device);
        DeviceClasses device2 = warehouse.getDevices().getLast();

        assertEquals(device, device2);

    }

    @Test
    public void testGetDeviceById() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(device);
        DeviceClasses device2 = warehouse.getDeviceById(device.getId());

        assertEquals(device, device2);

    }
}