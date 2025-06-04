import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.*;

public class AddItemsinCanteen {
    static List<EachItemData> items = new ArrayList<EachItemData>();
    
    private static void saveItemToDB(EachItemData i) {
        String query = "INSERT INTO canteen_items (id, item_name, qty, price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, i.getId());
            ps.setString(2, i.getItem_name());
            ps.setInt(3, i.getQty());
            ps.setDouble(4, i.getPrice());

            ps.executeUpdate();
            System.out.println("Item saved to DB: " + i.getItem_name());

        } catch (Exception e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }

    static void addITEMS() {
        Scanner scan = new Scanner(System.in);

        for (int index = 0; index < 1; index++) {

            System.out.println("Enter item name : ");
            String name = scan.next();

            System.out.println("Enter avl QTY : ");
            int qty = scan.nextInt();

            System.out.println("Enter Price : ");
            double price = scan.nextDouble();

            EachItemData i = new EachItemData(); 
            i.setItem_name(name);
            i.setPrice(price);
            i.setQty(qty);

            saveItemToDB(i); 
        }
    }

    static void updatePrice(int id, int updatePrice) {
        String query = "UPDATE canteen_items SET price = ? WHERE id = ?";
        
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setDouble(1, updatePrice);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();
            if(rows > 0) {
                System.out.println("Price updated successfully for item ID " + id);
            }else{
                System.out.println("Not a valid Id");
            }

        } catch (SQLException e) {
            System.out.println("Error while updating price: " + e.getMessage());
        }
    }

    
    static void deleteItem(int id) {
        String query = "DELETE FROM canteen_items WHERE id = ?";

        try{
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if(rows > 0){
                System.out.println("Item deleted successfully for ID " + id);
            } else {
                System.out.println("Not a valid Id");
            }

        } catch (SQLException e) {
            System.out.println("Error while deleting item: " + e.getMessage());
        }
    }

 static void AllitemsInCanteenAvL() {

        String query = "SELECT id, item_name, price, qty FROM canteen_items";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            conn = DBConnection.getConnection();
            if(conn!=null){
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);

                while(rs.next()) {
                    int id = rs.getInt("id");
                    String itemName = rs.getString("item_name");
                    double price = rs.getDouble("price");
                    int qty = rs.getInt("qty");

                    System.out.println("ID: " + id + " Item_name: " + itemName + " ItemPrice: " + price + " AvlQty: " + qty);

                }
            }
        }catch(SQLException e){
            System.out.println("Error while printing All item list printing");
        }
    }

    static void OrderListUser(String sessionId) {
        String query = "SELECT id, item_name, qty, price FROM user_orders WHERE session_id = '" + sessionId + "'";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            conn = DBConnection.getConnection();
            if(conn!=null){
                System.out.println("Enter connection");
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);

                while(rs.next()) {
                    int id = rs.getInt("id");
                    String itemName = rs.getString("item_name");
                    double price = rs.getDouble("price");
                    int qty = rs.getInt("qty");

                    System.out.println("ID: " + id + " Item_name: " + itemName + " ItemPrice: " + price + " Qty: " + qty);

                }
            }
        }catch(SQLException e){
            System.out.println("Error while printing item list printing");
        }
    }

    static void cancelOrderFunction(String sessionId, int id, int q) {

        Connection conn = DBConnection.getConnection();
        if(conn == null) {
            System.out.println("Unable to connect to the database.");
            return;
        }

        try{
            String getOrder = "SELECT * FROM user_orders WHERE session_id = ? AND id = ?";
            PreparedStatement psOrder = conn.prepareStatement(getOrder);
            psOrder.setString(1, sessionId);
            psOrder.setInt(2, id);
            ResultSet rs = psOrder.executeQuery();
            if(!rs.next()) {
                System.out.println("Order not found.");
                return;
            }

            int orderQty = rs.getInt("qty");
            String itemName = rs.getString("item_name");
            double price = rs.getDouble("price");

            if(q > orderQty) {
                System.out.println("SORRY: You cannot cancel more than ordered.");
                return;
            }

            String updateStock = "UPDATE canteen_items SET qty = qty + ? WHERE item_name = ?";
            PreparedStatement psStock = conn.prepareStatement(updateStock);
            psStock.setInt(1, q);
            psStock.setString(2, itemName);
            psStock.executeUpdate();

            if(q == orderQty){ 
                String deleteOrder = "DELETE FROM user_orders WHERE id = ?";
                PreparedStatement psDelete = conn.prepareStatement(deleteOrder);
                psDelete.setInt(1, id);
                psDelete.executeUpdate();

            } else { 
                String updateOrder = "UPDATE user_orders SET qty = qty - ? WHERE id = ?";
                PreparedStatement psUpdate = conn.prepareStatement(updateOrder);
                psUpdate.setInt(1, q);
                psUpdate.setInt(2, id);
                psUpdate.executeUpdate();
            }
        } catch (SQLException  e) {
            System.out.println("Error while Cancle order by customer");
        }


        // boolean flag=false;
        // for(int i=0;i<items.size();i++){
        //     if(items.get(i).getId()==id){
        //         flag=true;
        //     }
        // }
        // if(flag==false){
        //     System.out.println("Not a valid Id");
        //     return;
        // }
        // id--;
        // if (l.isEmpty()) {
        //     System.out.println("SORRY :<<<>>>:<<<<>>>:");
        //     return;
        // }
        // UserListData eid = (UserListData) l.get(id);// take bill id
        // if (eid.getQty() < q) {// if by mistake use enter more then ordered qty
        //     System.out.println("SORRY ::::<<>>::::::");
        //     return;
        // }
        // int qty = eid.getQty();
        // if (q == qty) {// remove
        //     l.remove(id);
        // } else {
        //     // update the list
        //     ((UserListData) l.get(id)).setQty(qty - q);

        //     boolean flag1 = true;
        //     if (!items.isEmpty()) {
        //         for (EachItemData itm : items) {
        //             if (itm.getId() == id + 1) {
        //                 int qty1 = itm.getQty();
        //                 itm.setQty(qty1 + q);
        //                 flag1 = false;
        //                 break;
        //             }
        //         }
        //         // System.out.println(items);
        //     }

        //     if (flag1 == true) {// if item is finish then remove after added then new add available QTY
        //         EachItemData eld = new EachItemData();
        //         eld.setId(eid.getId());
        //         eld.setItem_name(eid.getItem_name());
        //         eld.setPrice(eid.getPrice());
        //         eld.setQty(eid.getQty());
        //         items.add(eld);
        //     }
        // }

    }



    static void takeOrderFunction(List<UserListData> l, int id, int q, String sessionId) { 
        
        Connection conn = DBConnection.getConnection();
        if(conn == null) {
            System.out.println("Unable to connect to the database.");
            return;
        }
        String query = "SELECT * FROM canteen_items WHERE id = ?"; 
        try{
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                int itemId = rs.getInt("id");
                String itemName = rs.getString("item_name");
                int availableQty = rs.getInt("qty");
                double price = rs.getDouble("price");

                if(availableQty <= 0 || availableQty < q) {
                    System.out.println("SORRY: Not enough stock for the item.");
                    return;
                }

                String updateQuery = "UPDATE canteen_items SET qty = ? WHERE id = ?";
                try{
                    PreparedStatement updatePs = conn.prepareStatement(updateQuery);
                    updatePs.setInt(1, availableQty - q);
                    updatePs.setInt(2, itemId);
                    updatePs.executeUpdate();

                    String insertOrderQuery = "INSERT INTO user_orders (session_id, item_name, qty, price, created_at) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";

                    try {

                        PreparedStatement insertPs = conn.prepareStatement(insertOrderQuery);
                        insertPs.setString(1, sessionId); 
                        insertPs.setString(2, itemName);
                        insertPs.setInt(3, q);
                        insertPs.setDouble(4, price);
                        insertPs.executeUpdate();
                        System.out.println("Order placed successfully.");

                    } catch (SQLException  e) {
                       System.out.println("Error while insert order of the customer");
                    }

                } catch (SQLException  e) {
                    System.out.println("Error while updating item");
                }

            }


        } catch (SQLException  e) {
            System.out.println("Error while connect with the database");
        }
    }
    

}
