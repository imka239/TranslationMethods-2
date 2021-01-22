import java.util.Arrays;
import java.util.List;

public class Tree {
    private static int counter;
    private String node;
    private int myCounter;
    private List<Tree> children;

    public Tree(String node, Tree... children) {
        this.node = node;
        this.myCounter = counter;
        counter++;
        this.children = Arrays.asList(children);
    }

    public Tree(String node, List<Tree> children) {
        this.node = node;
        this.myCounter = counter;
        counter++;
        this.children = children;
    }


    public String getNode() {
        return node;
    }

    public int getCounter() {
        return myCounter;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public String checkTree(int h) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < h; i++) {
            stringBuilder.append("\t");
        }
        stringBuilder.append(node + "\n");
        for (Tree tree : children) {
            stringBuilder.append(tree.checkTree(h + 1));
        }
        return stringBuilder.toString();
    }

    public String printTree() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(myCounter + " [label = \"" + node + "\"];\n");
        for (Tree tree : children) {
            stringBuilder.append(myCounter + " -> " + tree.getCounter() + ";\n");
            stringBuilder.append(tree.printTree());
        }
        return stringBuilder.toString();
    }
    //digraph G {
    //0 [label = "E"];
    //0 -> 1;
    //1 [label = "("];
    //}
}
