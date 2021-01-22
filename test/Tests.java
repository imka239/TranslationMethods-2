import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Random;

public class Tests {

    private String generate(int h) {
        Random random = new Random();
        int x = ((random.nextInt() % 100) + 100) % 100;
        if (x > h) {
            return "Word";
        } else {
            int val = (random.nextInt() % 7 + 7) % 7 + 1;
            StringBuilder str = new StringBuilder();
            str.append("Word<");
            for (int i = 0; i < val; i++) {
                if (i < val - 1) {
                    str.append(generate(h / 2)).append(", ");
                } else {
                    str.append(generate(h / 2));
                }
            }
            str.append(">");
            return str.toString();
        }
    }

    @Test
    public void testInt() {
        try {
            Parser parser = new Parser();
            String easyInt = "var x:Int;";
            Tree ans = parser.parse(easyInt);
            assert(ans.checkTree(0).equals("" +
                    "S\n" +
                    "\tvar\n" +
                    "\tN\n" +
                    "\t\tname\n" +
                    "\t\tNPrime\n" +
                    "\t\t\t:\n" +
                    "\t\t\tT\n" +
                    "\t\t\t\tWord\n" +
                    "\t\t\t;\n"));
            String tree = ans.printTree();
            FileWriter writer = new FileWriter("graph1.txt", false);
            writer.write("digraph G {\n");
            writer.write(tree);
            writer.write("}");
            writer.flush();
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRandom60() {
        try {
            Parser parser = new Parser();
            String toParse = "var q10e :     " + generate(400) + ";";
            System.out.println(toParse);
            Tree ans = parser.parse(toParse);
            String tree = ans.printTree();
            FileWriter writer = new FileWriter("graph60.txt", false);
            writer.write("digraph G {\n");
            writer.write(tree);
            writer.write("}");
            writer.flush();
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testArray() {
        try {
            Parser parser = new Parser();
            String easyArray = "var x:Array<Double, INt>;";
            Tree ans = parser.parse(easyArray);
            System.out.println(ans.checkTree(0));

            String tree = ans.printTree();
            FileWriter writer = new FileWriter("graph2.txt", false);
            writer.write("digraph G {\n");
            writer.write(tree);
            writer.write("}");
            writer.flush();
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMap() {
        try {
            Parser parser = new Parser();
            String easyMap = "var x:Map<Double, Int>;";
            Tree ans = parser.parse(easyMap);
            System.out.println(ans.checkTree(0));
            assert(ans.checkTree(0).equals("" +
                    "S\n" +
                    "\tvar\n" +
                    "\tN\n" +
                    "\t\tname\n" +
                    "\t\tNPrime\n" +
                    "\t\t\t:\n" +
                    "\t\t\tT\n" +
                    "\t\t\t\tWord\n" +
                    "\t\t\t\t<\n" +
                    "\t\t\t\tT\n" +
                    "\t\t\t\t\tWord\n" +
                    "\t\t\t\tT\n" +
                    "\t\t\t\t\tWord\n" +
                    "\t\t\t\t>\n" +
                    "\t\t\t;\n"));
            String tree = ans.printTree();
            FileWriter writer = new FileWriter("graph3.txt", false);
            writer.write("digraph G {\n");
            writer.write(tree);
            writer.write("}");
            writer.flush();
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_() {
        try {
            Parser parser = new Parser();
            String easyInt = "var _:Int;";
            Tree ans = parser.parse(easyInt);
            assert(ans.checkTree(0).equals("" +
                    "S\n" +
                    "\tvar\n" +
                    "\tN\n" +
                    "\t\tname\n" +
                    "\t\tNPrime\n" +
                    "\t\t\t:\n" +
                    "\t\t\tT\n" +
                    "\t\t\t\tWord\n" +
                    "\t\t\t;\n"));
            String tree = ans.printTree();
            FileWriter writer = new FileWriter("graph5.txt", false);
            writer.write("digraph G {\n");
            writer.write(tree);
            writer.write("}");
            writer.flush();
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMapWrong1() {
        try {
            Parser parser = new Parser();
            String wrongMap1 = "var x:Map<Double, >;";
            parser.parse(wrongMap1);
            assert(false);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        } catch (AssertionError e) {
            System.out.println("error as expected");
        }
    }

    @Test
    public void testMapWrong2() {
        try {
            Parser parser = new Parser();
            String wrongMap2 = "var x:Map<Double, In>;";
            parser.parse(wrongMap2);
            assert(false);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        } catch (AssertionError e) {
            System.out.println("error as expected");
        }
    }

    @Test
    public void testArrayArray() {
        try {
            Parser parser = new Parser();
            String normalArray = "var x:Array<Array<Int>>;";
            Tree ans = parser.parse(normalArray);
            String tree = ans.printTree();
            FileWriter writer = new FileWriter("graph4.txt", false);
            writer.write("digraph G {\n");
            writer.write(tree);
            writer.write("}");
            writer.flush();
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testArrayWrong() {
        try {
            Parser parser = new Parser();
            String normalArray = "var x:Array<Array<>>;";
            parser.parse(normalArray);
            assert(false);
        } catch (AssertionError e) {
            System.out.println("error as expected");
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testNameWrong() {
        try {
            Parser parser = new Parser();
            String digitArray = "var 9999:Array<Array<Int>>;";
            parser.parse(digitArray);
            assert(false);
        } catch (AssertionError e) {
            System.out.println("error as expected");
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testvarvar() {
        try {
            Parser parser = new Parser();
            String digitArray = "var var:Array<Array<Int>>;";
            parser.parse(digitArray);
            assert(false);
        } catch (AssertionError e) {
            System.out.println("error as expected");
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testvarArray() {
        try {
            Parser parser = new Parser();
            String digitArray = "var Array:Array<Array<Int>>;";
            parser.parse(digitArray);
            assert(false);
        } catch (AssertionError e) {
            System.out.println("error as expected");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testvarInt() {
        try {
            Parser parser = new Parser();
            String digitArray = "var Int:Array<Array<Int>>;";
            parser.parse(digitArray);
            assert(false);
        } catch (AssertionError e) {
            System.out.println("error as expected");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testvarint() {
        try {
            Parser parser = new Parser();
            String digitArray = "var int:Array<Array<Int>>;";
            parser.parse(digitArray);
            assert(false);
        } catch (AssertionError e) {
            System.out.println("error as expected");
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testvarRussian() {
        try {
            Parser parser = new Parser();
            String digitArray = "var АндрейСергеевич:Преподаватель<Учитель<Человек>>;";
            parser.parse(digitArray);
            assert(false);
        } catch (AssertionError e) {
            System.out.println("error as expected");
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testVAR() {
        try {
            Parser parser = new Parser();
            String digitArray = "VAR x:Int;";
            parser.parse(digitArray);
            assert(false);
        } catch (AssertionError e) {
            System.out.println("error as expected");
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testTuple() {
        try {
            Parser parser = new Parser();
            String digitArray = "VAR x:Int<Int<INt>, Int>;";
            parser.parse(digitArray);
            assert(false);
        } catch (AssertionError e) {
            System.out.println("error as expected");
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }
}
