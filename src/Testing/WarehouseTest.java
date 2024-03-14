package Testing;

import Devices.DeviceClasses;
import WarehouseManagement.Warehouse;
import org.junit.Test;


import java.util.List;

import static org.junit.Assert.*;

public class WarehouseTest {

    @Test
    public void testAddDevice_realDevice() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(device);
        DeviceClasses device2 = warehouse.getDevices().getLast();

        assertEquals(device, device2);
    }

    @Test
    public void testAddDevice_nullDevice() {
        Warehouse warehouse = new Warehouse();
        warehouse.addDevice(null);

        assertEquals(0, warehouse.getDevices().size());
    }

    @Test
    public void testGetDeviceById_existingId() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(device);
        DeviceClasses device2 = warehouse.getDeviceById(device.getId());

        assertEquals(device, device2);

    }

    @Test
    public void testGetDeviceById_nullId() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(device);
        DeviceClasses device2 = warehouse.getDeviceById(null);

        assertNull(device2);
    }

    @Test
    public void testGetDeviceById_wrongId() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(device);
        DeviceClasses device2 = warehouse.getDeviceById(254875648L);

        assertNull(device2);
    }

    @Test
    public void testRemoveDeviceById_existingDevice() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(device);
        warehouse.removeDeviceById(device.getId());

        assertFalse(warehouse.containsDeviceById(device.getId()));
    }

    @Test
    public void testRemoveDeviceById_nullDevice() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(device);
        boolean removedDevice = warehouse.removeDeviceById(null);

        assertFalse(removedDevice);
    }

    @Test
    public void testRemoveDeviceById_wrongDevice() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(warehouse.setId(device));
        DeviceClasses anotherDevice = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        boolean removedDevice = warehouse.removeDeviceById(anotherDevice.getId());

        assertFalse(removedDevice);
    }

    @Test
    public void testContainsDeviceById_existingDevice() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(device);

        assertTrue(warehouse.containsDeviceById(device.getId()));
    }

    @Test
    public void testContainsDeviceById_nullDevice() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(warehouse.setId(device));
        boolean removedDevice = warehouse.containsDeviceById(null);

        assertFalse(removedDevice);
    }

    @Test
    public void testGetCompatibles_existingDevice() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(warehouse.setId(device));
        List<DeviceClasses> result = warehouse.getCompatibles("Notebook", "device");

        assertTrue(result.contains(device));
    }

    @Test
    public void testGetCompatibles_nullDevice() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device1 = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        DeviceClasses device2 = new DeviceClasses(979, "Smartphone", "Apple", "iPhone 15", "Nero", 6.1, 128, 349);
        warehouse.addDevice(warehouse.setId(device1));
        warehouse.addDevice(warehouse.setId(device2));

        assertNull(warehouse.getCompatibles(null, null));
    }

    @Test
    public void testGetCompatibles_wrongDevice() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(warehouse.setId(device));
        List<DeviceClasses> result = warehouse.getCompatibles("Typo", "device");

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetRangeSale_existingDevice() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(warehouse.setId(device));
        List<DeviceClasses> result = warehouse.getRangeSale(0, 2000);

        assertTrue(result.contains(device));
    }

    @Test
    public void testGetRangeSale_nullInput() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(warehouse.setId(device));
        List<DeviceClasses> result = warehouse.getRangeSale(null, 2000);

        assertNull(result);
    }

    @Test
    public void testGetRangeSale_notExistingDevice() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(warehouse.setId(device));
        List<DeviceClasses> result = warehouse.getRangeSale(0, 1);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetBySellPrice_existingDevice() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(warehouse.setId(device));
        List<DeviceClasses> result = warehouse.getBySellPrice(1500);

        assertTrue(result.contains(device));
    }

    @Test
    public void testGetBySellPrice_nullInput() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(warehouse.setId(device));
        List<DeviceClasses> result = warehouse.getBySellPrice(null);

        assertNull(result);
    }

    @Test
    public void testGetBySellPrice_notExistingDevice() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(warehouse.setId(device));
        List<DeviceClasses> result = warehouse.getBySellPrice(1);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetByPurchasePrice_existingDevice() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(warehouse.setId(device));
        List<DeviceClasses> result = warehouse.getByPurchasePrice(799);

        assertTrue(result.contains(device));
    }

    @Test
    public void testGetByPurchasePrice_nullInput() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(warehouse.setId(device));
        List<DeviceClasses> result = warehouse.getByPurchasePrice(null);

        assertNull(result);
    }

    @Test
    public void testGetByPurchasePrice__notExistingDevice() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(warehouse.setId(device));
        List<DeviceClasses> result = warehouse.getByPurchasePrice(1);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetAverageDevicePrice_existingDevice() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device1 = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        DeviceClasses device2 = new DeviceClasses(979, "Notebook", "Apple", "iPhone 15", "Nero", 6.1, 128, 349);
        warehouse.addDevice(warehouse.setId(device1));
        warehouse.addDevice(warehouse.setId(device2));
        double result = warehouse.getAverageDevicePrice("Notebook");

        assertEquals(574, result, 0.0);
    }

    @Test
    public void testGetAverageDevicePrice_nullDevice() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device1 = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        DeviceClasses device2 = new DeviceClasses(979, "Notebook", "Apple", "iPhone 15", "Nero", 6.1, 128, 349);
        warehouse.addDevice(warehouse.setId(device1));
        warehouse.addDevice(warehouse.setId(device2));
        Double result = warehouse.getAverageDevicePrice(null);

        assertNull(result);
    }

    @Test
    public void testGetAverageDevicePrice_notExistingDevice() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device1 = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(warehouse.setId(device1));
        Double result = warehouse.getAverageDevicePrice("Patata");

        assertNull(result);
    }

}