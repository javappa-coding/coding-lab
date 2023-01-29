public class Start {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Item item1 = new Item(1L, "Your first item", "desc1");
        Item item2 = new Item(1L, "Your first item", "desc2");

        System.out.println(item1);
        System.out.println(item2);

        System.out.println(item1.equals(item2));

        System.out.println("======================");

        BestItem bestItem1 = new BestItem(1L, "your best item no 1", "desc1");
        BestItem bestItem2 = new BestItem(1L, "your best item no 1", "desc1");

        System.out.println(bestItem1);
        System.out.println(bestItem2);

        System.out.println(bestItem1.equals(bestItem2));
    }
}