import Devices.DeviceClasses;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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

    @Test
    public void testRemoveDeviceById() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(device);
        warehouse.removeDeviceById(device.getId());

        assertFalse(warehouse.containsDeviceById(device.getId()));
    }

    @Test
    public void testContainsDeviceById() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(device);

        assertTrue(warehouse.containsDeviceById(device.getId()));
    }

    @Test
    public void testPrintAllDevices() {
        DeviceClasses device1 = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        DeviceClasses device2 = new DeviceClasses(979, "Smartphone", "Apple", "iPhone 15", "Nero", 6.1, 128, 349);
        List<DeviceClasses> devices = new ArrayList<>();
        devices.add(device1);
        devices.add(device2);
        Warehouse warehouse = new Warehouse();
        warehouse.printAllDevices(true, true);

        assertTrue("questi sono i miei device: " + devices, true);
    }

    @Test
    public void testGetCompatibles() {
        DeviceClasses device1 = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        DeviceClasses device2 = new DeviceClasses(979, "Smartphone", "Apple", "iPhone 15", "Nero", 6.1, 128, 349);
        List<DeviceClasses> devices = new ArrayList<>();
        devices.add(device1);
        devices.add(device2);

        assertEquals("Notebook", devices.get(0).getDevice());
        assertEquals("Apple", devices.get(1).getBrand());
    }
}