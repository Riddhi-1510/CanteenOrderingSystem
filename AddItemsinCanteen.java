import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddItemsinCanteen {
    static List<EachItemData> items = new ArrayList<EachItemData>(2);

    static void addITEMS() {
        Scanner scan = new Scanner(System.in);

        for (int index = 0; index < 2; index++) {
            System.out.println("Enter id :");
            int id = scan.nextInt();

            System.out.println("Enter item name : ");
            String name = scan.next();

            System.out.println("Enter avl QTY : ");
            int qty = scan.nextInt();

            System.out.println("Enter Price : ");
            double price = scan.nextDouble();

            EachItemData i = new EachItemData();
            i.setId(id);
            i.setItem_name(name);
            i.setPrice(price);
            i.setQty(qty);

            items.add(i);
        }
        scan.close();
    }

    static void AllitemsInCanteenAvL() {
        items.forEach(item -> {
            System.out.println("ID  : " + item.getId() + " Item_name : " + item.getItem_name() + " ItemPrice : "
                    + item.getPrice() + " AvlQty : " + item.getQty());
        });
    }

    static void OrderListUser(List<UserListData> l) {
        double totalAmount = 0;
        for (UserListData item : l){

            totalAmount += (item.getQty() * item.getPrice());

            System.out.println("ID  : " + item.getId() + " Item_name : " + item.getItem_name() + " ItemPrice : "
                    + item.getPrice() + " totalQty : " + item.getQty());
        }

        System.out.println("TOTAL amount is : " + totalAmount);
    }

    static void cancelOrderFunction(List<UserListData> l, int id, int q) {
        id--;
        if(l.isEmpty()){
            System.out.println("SORRY :<<<>>>:<<<<>>>:");
            return;
        }
        UserListData eid = (UserListData) l.get(id);// take bill id
        if(eid.getQty() < q){
            System.out.println("SORRY ::::<<>>::::::");
            return;
        }
        int qty = eid.getQty();
        if (q == qty) {// remove
            l.remove(id);
        } else if (qty - q < 0) {
            System.out.println("ERROR NOT POSSIBLE SORRY :<");
        } else {
            // update the list
            ((UserListData) l.get(id)).setQty(qty - q);

            boolean flag = true;
            if(!items.isEmpty()){
                System.out.println("Enter ");
                for (EachItemData itm : items) {
                    if(itm.getId()==id+1){
                        int qty1 = itm.getQty();
                        itm.setQty(qty1 + q);
                        flag=false;
                        break;
                    }
                }
                // System.out.println(items);
            } 

            if(flag==true){
                EachItemData eld = new EachItemData();
                eld.setId(eid.getId());
                eld.setItem_name(eid.getItem_name());
                eld.setPrice(eid.getPrice());
                eld.setQty(eid.getQty());
                items.add(eld);
            }
        }

    }

    static void takeOrderFunction(List<UserListData> l, int id, int q) {
        id--;
        
        EachItemData eid = items.get(id);

        if(eid.getQty()<=0 || eid.getQty() < q){
            System.out.println("SORRY :<<<<<>>>>>:");
            return;
        }

        if (q <= eid.getQty()) {
            int qty = eid.getQty();
            items.get(id).setQty(qty - q);// update QTY in main list
            if ((qty - q) == 0) {// if QTY is ZERO then delete item
                items.remove(id);
            }
            // System.out.println(items);
        }
        boolean flag = true;
        if(!l.isEmpty()){
            // System.out.println("Enter ");
            for (UserListData itm : l) {
                if(itm.getId()==id+1){
                        
                    int qty = l.get(id).getQty();
                    l.get(id).setQty(q + qty);
                    flag = false;
                    break;
                    
                }
            }
            // System.out.println(items);
        } 
        if(flag==true){
            UserListData uld = new UserListData();
            uld.setId(eid.getId());
            uld.setItem_name(eid.getItem_name());
            uld.setPrice(eid.getPrice());
            uld.setQty(eid.getQty());

            l.add(uld);
            l.get(id).setQty(q);// update QTY in User List
            
        }
        // System.out.println(items);
    }
}
