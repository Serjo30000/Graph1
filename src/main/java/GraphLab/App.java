package GraphLab;

import GraphLab.graphComponent.MathGraph;

public class App {
    public static void main(String[] args){
        MathGraph mathGraph1 = new MathGraph();
        mathGraph1.addNode();
        mathGraph1.addNode();
        mathGraph1.addNode();
        mathGraph1.addNode();
        mathGraph1.addEdge(1,2,3);
        mathGraph1.addEdge(1,3,4);
        mathGraph1.addEdge(1,4,5);
        mathGraph1.addEdge(2,4,3);
        mathGraph1.addEdge(3,4,3);
        mathGraph1.removeEdge(1,3);
        System.out.println("Число вершин: "+mathGraph1.countNode());
        System.out.println("Число ребер: "+mathGraph1.countEdge());
        System.out.println("Вес ребра: "+mathGraph1.getWeight(1,4));
        System.out.println("Проверка на смежность: "+mathGraph1.checkAdjacency(1,4));
        System.out.println("Проверка на смежность: "+mathGraph1.checkAdjacency(3,2));
        System.out.println("\n");
        MathGraph mathGraph2 = new MathGraph("C:\\Games\\graphs\\graph1.txt","Списки");
        System.out.println("Число вершин: "+mathGraph2.countNode());
        System.out.println("Число ребер: "+mathGraph2.countEdge());
        System.out.println("Вес ребра: "+mathGraph2.getWeight(1,3));
        System.out.println("Проверка на смежность: "+mathGraph2.checkAdjacency(1,5));
        System.out.println("Проверка на смежность: "+mathGraph2.checkAdjacency(3,2));
        System.out.println("\n");
        MathGraph mathGraph3 = new MathGraph("C:\\Games\\graphs\\graph2.txt","Матрица");
        System.out.println("Число вершин: "+mathGraph3.countNode());
        System.out.println("Число ребер: "+mathGraph3.countEdge());
        System.out.println("Вес ребра: "+mathGraph3.getWeight(1,2));
        System.out.println("Проверка на смежность: "+mathGraph3.checkAdjacency(1,4));
        System.out.println("Проверка на смежность: "+mathGraph3.checkAdjacency(3,4));
        System.out.println("\n");
        mathGraph2.saveGraph("graph3.txt","C:\\Games\\graphs\\","Матрица");
        mathGraph3.saveGraph("graph4.txt","C:\\Games\\graphs\\","Списки");
        mathGraph3.sortListNode("По убыванию");
        mathGraph3.sortListNode("По возрастанию");
    }
}
