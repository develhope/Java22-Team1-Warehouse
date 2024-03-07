package Testing;

import Devices.DeviceClasses;
import Devices.Smartphone;
import Devices.Tablet;
import MenusMethods.OperatorMethods;
import UserAndOperatorEnums.MenuOptionsOperator;
import Utils.GetValidInput;
import WarehouseManagement.Warehouse;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;


public class OperatorMethodsTest {

    @Test
    public void addNewDevice_test() {

    }

    @Test
    public void switchDevice_validInput_returnsCorrectDevice() {
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Warehouse warehouse = new Warehouse();

        OperatorMethods operatorMethods = new OperatorMethods(warehouse);

        String result = operatorMethods.switchDevice();

        assertEquals("Smartphone", result);
    }

//    @Test
//    public void switchDevice_invalidInput_returnsErrorMessage() {
//        String input = "4";
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//        Warehouse warehouse = new Warehouse();
//
//        OperatorMethods operatorMethods = new OperatorMethods(warehouse, new Scanner(System.in));
//
//        String result = operatorMethods.switchDevice();
//
//        assertEquals("Scelta non valida", result);
//    }

    @Test
    public void searchByAverageDevicePrice_validInput_returnsCorrectString() {
        String input = "Tablet";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Warehouse warehouse = new Warehouse();
        warehouse.addDevice(new Tablet(1149, "Tablet", "Apple", "iPad Pro", "Grigio Siderale", 11, 256, 549));
        OperatorMethods operatorMethods = new OperatorMethods(warehouse);

        String result = operatorMethods.searchByAverageDevicePrice();

        assertEquals("Il prezzo medio per " + input + " è: " + warehouse.getDevices().getFirst().getPurchase(), result);
    }

    @Test
    public void searchByAverageDevicePrice_validInput_returnsErrorMessage() {
        String input = "Culetto Umido";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Warehouse warehouse = new Warehouse();
        warehouse.addDevice(new Tablet(1149, "Tablet", "Apple", "iPad Pro", "Grigio Siderale", 11, 256, 549));

        OperatorMethods operatorMethods = new OperatorMethods(warehouse);

        String result = operatorMethods.searchByAverageDevicePrice();

        assertEquals("Errore: inserisci un device valido!", result);
    }

    @Test
    public void searchByPurchasePrice_validInput_returnsCorrectList() {
        String input = "4000";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Warehouse warehouse = new Warehouse();

        warehouse.addDevice(new Tablet(1000, "Tablet", "Brand", "Model", "Color", 10, 128, 300));
        warehouse.addDevice(new Smartphone(800, "Smartphone", "Brand", "Model", "Color", 10, 64, 4000));

        OperatorMethods operatorMethods = new OperatorMethods(warehouse);


        List<DeviceClasses> result = operatorMethods.searchByPurchasePrice();

        assertEquals(1, result.size());
    }

    @Test
    public void searchByPurchasePrice_invalidInput_returnsEmptyList() {
        String input = "10000";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Warehouse warehouse = new Warehouse();

        warehouse.addDevice(new Tablet(1000, "Tablet", "Brand", "Model", "Color", 10, 128, 300));
        warehouse.addDevice(new Smartphone(800, "Smartphone", "Brand", "Model", "Color", 10, 64, 4000));

        OperatorMethods operatorMethods = new OperatorMethods(warehouse);


        List<DeviceClasses> result = operatorMethods.searchByPurchasePrice();

        assertEquals(0, result.size());
    }

    @Test
    public void removeFromWarehouseById_validInput_removesDevice() {
        Warehouse warehouse = new Warehouse();
        warehouse.addDevice(new Tablet(1000, "Tablet", "Brand", "Model", "Color", 10, 128, 300));

        String input = String.valueOf(warehouse.getDevices().getFirst().getId());
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        OperatorMethods operatorMethods = new OperatorMethods(warehouse);
        operatorMethods.removeFromWarehouseById();

        assertEquals(0, warehouse.getDevices().size());
    }

    @Test
    public void removeFromWarehouseById_invalidInput_doesntRemoveDevice() {
        Warehouse warehouse = new Warehouse();
        warehouse.addDevice(new Tablet(1000, "Tablet", "Brand", "Model", "Color", 10, 128, 300));

        String input = "131231231";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        OperatorMethods operatorMethods = new OperatorMethods(warehouse);
        operatorMethods.removeFromWarehouseById();

        assertEquals(1, warehouse.getDevices().size());
    }

    @Test
    public void getMenuOptionsByIndex_validIndex_returnsMenuOption() {
        GetValidInput getValidInput = new GetValidInput();
        String input = "0";
        MenuOptionsOperator expected = MenuOptionsOperator.VISUALIZZA_TUTTI_PRODOTTI;
        MenuOptionsOperator result = getValidInput.getMenuOptionsByIndex(input);

        assertEquals(expected, result);
    }

    @Test
    public void getMenuOptionsByIndex_invalidIndex_returnsUnknown() {
        GetValidInput getValidInput = new GetValidInput();
        String input = "14";
        MenuOptionsOperator expected = MenuOptionsOperator.UNKNOWN;
        MenuOptionsOperator result = getValidInput.getMenuOptionsByIndex(input);

        assertEquals(expected, result);
    }

    @Test
    public void addNewDevice_returnsDeviceClasses() {
        String input = "1\n" +
                "Brand\n" +
                "Model\n" +
                "Description\n" +
                "6.0\n" +
                "256\n" +
                "500.0\n" +
                "600.0";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        OperatorMethods operatorMethods = new OperatorMethods(new Warehouse());

        DeviceClasses result = operatorMethods.addNewDevice();

        assertEquals("Smartphone", result.getDevice());
        assertEquals("Brand", result.getBrand());
        assertEquals("Model", result.getModel());
        assertEquals("Description", result.getDescription());
        assertEquals(6.0, result.getDisplay(), 0.01);
        assertEquals(256, result.getStorage(), 0);
        assertEquals(500.0, result.getPurchase(), 0.01);
        assertEquals(600.0, result.getSale(), 0.01);
    }

    @Test
    public void setIdAddDeviceInWarehouse_deviceAddedSuccessfully() {
        Warehouse warehouse = new Warehouse();
        DeviceClasses device = new DeviceClasses(500.0, "Smartphone", "Brand", "Model", "Description", 6.0, 256, 500.0);

        OperatorMethods operatorMethods = new OperatorMethods(warehouse);
        operatorMethods.setIdAddDeviceInWarehouse(device);

        assertTrue(warehouse.getDevices().contains(device));
        assertNotNull(device.getId());
    }
}
