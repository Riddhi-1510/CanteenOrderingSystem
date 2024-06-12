import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddItemsinCanteen {
    static List<EachItemData> items = new ArrayList<EachItemData>();
    static int itemid = 1;

    static void addITEMS() {
        Scanner scan = new Scanner(System.in);

        for (int index = 0; index < 1; index++) {
            int id = itemid;
            itemid++;

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
    }

    static void updatePrice(int id, int updatePrice) {
        boolean flag=false;
        for (EachItemData item : items) {
            if (item.getId() == id) {
                item.setPrice(updatePrice);
                flag=true;
                break;
            }
        }
        if(flag==false){
            System.out.println("Not a valid Id");
        }
    }
    static void deleteItem(int id) {
        boolean flag = false;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == id) {
                items.remove(i);
                flag=true;
                break; 
            }
        }
        if(flag==false){
            System.out.println("Not a valid Id");
        }
    }
    static void AllitemsInCanteenAvL() {
        items.forEach(item -> {
            System.out.println("ID  : " + item.getId() + " Item_name : " + item.getItem_name() + " ItemPrice : "
                    + item.getPrice() + " AvlQty : " + item.getQty());
        });
    }

    static void OrderListUser(List<UserListData> l) {
        double totalAmount = 0;
        for (UserListData item : l) {

            totalAmount += (item.getQty() * item.getPrice());

            System.out.println("ID  : " + item.getId() + " Item_name : " + item.getItem_name() + " ItemPrice : "
                    + item.getPrice() + " totalQty : " + item.getQty());
        }

        System.out.println("TOTAL amount is : " + totalAmount);
    }

    static void cancelOrderFunction(List<UserListData> l, int id, int q) {
        boolean flag=false;
        for(int i=0;i<items.size();i++){
            if(items.get(i).getId()==id){
                flag=true;
            }
        }
        if(flag==false){
            System.out.println("Not a valid Id");
            return;
        }
        id--;
        if (l.isEmpty()) {
            System.out.println("SORRY :<<<>>>:<<<<>>>:");
            return;
        }
        UserListData eid = (UserListData) l.get(id);// take bill id
        if (eid.getQty() < q) {// if by mistake use enter more then ordered qty
            System.out.println("SORRY ::::<<>>::::::");
            return;
        }
        int qty = eid.getQty();
        if (q == qty) {// remove
            l.remove(id);
        } else {
            // update the list
            ((UserListData) l.get(id)).setQty(qty - q);

            boolean flag1 = true;
            if (!items.isEmpty()) {
                for (EachItemData itm : items) {
                    if (itm.getId() == id + 1) {
                        int qty1 = itm.getQty();
                        itm.setQty(qty1 + q);
                        flag1 = false;
                        break;
                    }
                }
                // System.out.println(items);
            }

            if (flag1 == true) {// if item is finish then remove after added then new add available QTY
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
        boolean flag=false;
        for(int i=0;i<items.size();i++){
            if(items.get(i).getId()==id){
                flag=true;
            }
        }
        if(flag==false){
            System.out.println("Not a valid Id");
            return;
        }
        id--;

        EachItemData eid = items.get(id);

        if (eid.getQty() <= 0 || eid.getQty() < q) {
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
        boolean flag1 = true;
        if (!l.isEmpty()) {// update the user list
            for (UserListData itm : l) {
                if (itm.getId() == id + 1) {

                    int qty = l.get(id).getQty();
                    l.get(id).setQty(q + qty);
                    flag1 = false;
                    break;

                }
            }
            // System.out.println(items);
        }
        if (flag1 == true) {// if user fresh order then create new food item
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
