import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class AppAllTest{

    @Test
    public void testAddItemsToList(){
        EachItemData item = new EachItemData();
        item.setId(1);
        item.setItem_name("Samosa");
        item.setQty(10);
        item.setPrice(15.0); 

        assertEquals("Samosa", item.getItem_name());
        assertEquals(10, item.getQty());
        assertEquals(15.0, item.getPrice(), 0.001);
    }

    @Test
    public void testUpdatePriceWithInvalidId(){
        //print "Not a valid Id"
        AddItemsinCanteen.updatePrice(9999, 50); 
    }

    @Test
    public void testDeleteItemInvalidId(){
        //print "Not a valid Id"
        AddItemsinCanteen.deleteItem(9999);
    }

    @Test
    public void testAllitemsInCanteenAvL(){
        AddItemsinCanteen.AllitemsInCanteenAvL(); 
    }

    @Test
    public void testTakeOrderFunction(){
        EachItemData item = new EachItemData();
        item.setId(1);
        item.setItem_name("Tea");
        item.setQty(5);
        item.setPrice(10);

        List<UserListData> userList = new java.util.ArrayList<>();
        AddItemsinCanteen.takeOrderFunction(userList, 1, 2, "abc123");
    }

    @Test
    public void testCancelOrderFunction(){
        AddItemsinCanteen.cancelOrderFunction("abc123", 1, 2);
    }
}


