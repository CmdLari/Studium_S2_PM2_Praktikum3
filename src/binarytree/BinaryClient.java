package binarytree;

public class BinaryClient {

    public static void main(String[] args) {

        System.out.printf("\n--------------------------1.---------------------------");

        BinaryTree<Integer> treely = new BinaryTree<Integer>(20);
        System.out.printf("\n%s", treely);
        System.out.printf("\n%s", treely.process(node -> ++node));

        BinaryTree<String> treely2 = new BinaryTree<String>("Hi");
        System.out.printf("\n%s", treely2);
        System.out.printf("\n%s", treely2.process(node -> node+" Bye"));

        System.out.printf("\n--------------------------2.---------------------------");

        BinaryTree<Integer> treely4 = new BinaryTree<Integer>(20);
        System.out.printf("\n%s", treely4);
        System.out.printf("\n%s", treely4.process(node -> ++node, node -> node>19));

        BinaryTree<Integer> treely5 = new BinaryTree<Integer>(20);
        System.out.printf("\n%s", treely5);
        System.out.printf("\n%s", treely5.process(node -> ++node, node -> node>25));

        BinaryTree<String> treely6 = new BinaryTree<String>("Hi");
        System.out.printf("\n%s", treely6);
        System.out.printf("\n%s", treely6.process(node -> node+" Bye", node -> node.equals("Hi Bye")));

        System.out.printf("\n--------------------------3.---------------------------");

        BinaryTree<Integer> treely7 = new BinaryTree<Integer>(20);
        System.out.printf("\n%s", treely7);
        treely7.process(node -> ++node, node -> node>19, node -> System.out.printf("\n%s", node.toString()));

        BinaryTree<Integer> treely8 = new BinaryTree<Integer>(20);
        System.out.printf("\n%s", treely8);
        treely8.process(node -> ++node, node -> node>25, node -> System.out.printf("\n%s", node.toString()));

        BinaryTree<String> treely9 = new BinaryTree<String>("Hi");
        System.out.printf("\n%s", treely9);
        treely9.process(node -> node+" Bye", node -> node.equals("Hi Bye"), node -> System.out.printf("\n%s", node));
    }

}
