import Devices.DeviceClasses;
import MenusMethods.UserMethods;
import WarehouseManagement.Cart;
import WarehouseManagement.Warehouse;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class UserMethodsTest {
    @Test
    public void fromWarehouseToCart_CorrectId(){
        Warehouse warehouse = new Warehouse();
        Cart cart = new Cart();
        UserMethods userMethods = new UserMethods(warehouse,cart,false,new Scanner(System.in));
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(device);
        userMethods.fromWarehouseToCart(device.getId());
        assertEquals(1,cart.getDevices().size());
        assertEquals(0,warehouse.getDevices().size());
    }
    @Test
    public void fromWarehouseToCart_UncorrectId(){
        Warehouse warehouse = new Warehouse();
        Cart cart = new Cart();
        UserMethods userMethods = new UserMethods(warehouse,cart,false,new Scanner(System.in));
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(device);
        userMethods.fromWarehouseToCart(-2);
        assertEquals(0,cart.getDevices().size());
        assertEquals(1,warehouse.getDevices().size());
    }
    @Test
    public void fromCartToWarehouse_CorrectId(){
        Warehouse warehouse = new Warehouse();
        Cart cart = new Cart();
        UserMethods userMethods = new UserMethods(warehouse,cart,false,new Scanner(System.in));
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        cart.addDevice(device);
        userMethods.fromCartToWarehouse(device.getId());
        assertEquals(0,cart.getDevices().size());
        assertEquals(1,warehouse.getDevices().size());
    }
    @Test
    public void fromCartToWarehouse_UnCorrectId(){
        Warehouse warehouse = new Warehouse();
        Cart cart = new Cart();
        UserMethods userMethods = new UserMethods(warehouse,cart,false,new Scanner(System.in));
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        cart.addDevice(device);
        userMethods.fromCartToWarehouse(-2);
        assertEquals(1,cart.getDevices().size());
        assertEquals(0,warehouse.getDevices().size());
    }

}
