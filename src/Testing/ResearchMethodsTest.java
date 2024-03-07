package Testing;

import Devices.DeviceClasses;
import MenusMethods.ResearchMethods;
import WarehouseManagement.Warehouse;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ResearchMethodsTest {
    @Test
    public void searchByType_correctInput() {
        String input = "tablet";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Warehouse warehouse = new Warehouse();
        ResearchMethods researchMethods = new ResearchMethods(warehouse, false);
        DeviceClasses device = new DeviceClasses(1500, "Tablet", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);

        warehouse.addDevice(device);
        List<DeviceClasses> result = researchMethods.searchByType();

        assertTrue(result.contains(device));
    }

    @Test
    public void searchByType_wrongInput() {
        String input = "culetto";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Warehouse warehouse = new Warehouse();
        ResearchMethods researchMethods = new ResearchMethods(warehouse, false);
        DeviceClasses device = new DeviceClasses(1500, "Tablet", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);

        warehouse.addDevice(device);
        List<DeviceClasses> result = researchMethods.searchByType();

        assertTrue(result.isEmpty());
    }

    @Test
    public void searchByType_emptyWarehouse() {
        String input = "culetto";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Warehouse warehouse = new Warehouse();
        ResearchMethods researchMethods = new ResearchMethods(warehouse, false);

        List<DeviceClasses> result = researchMethods.searchByType();

        assertNull(result);
    }
}
