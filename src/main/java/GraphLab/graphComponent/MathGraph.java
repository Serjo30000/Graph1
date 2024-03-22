package GraphLab.graphComponent;

import java.io.*;
import java.util.*;

public class MathGraph {
    private Map<Integer,Node>nodeMap;
    public MathGraph(){
        nodeMap=new HashMap<Integer, Node>();
    }
    public MathGraph(String nameFile,String create){
        try{
            nodeMap=new HashMap<Integer, Node>();
            if (create.equals("Списки")){
                Scanner scanner = new Scanner(new File(nameFile));
                String countNode="";
                if (scanner.hasNextLine()){
                    String firstLine = scanner.nextLine();
                    Scanner lineScanner = new Scanner(firstLine);
                    if (lineScanner.hasNextLine()) {
                        countNode += lineScanner.nextLine();
                    }
                    lineScanner.close();
                }
                scanner.close();
                int countNodeInt=Integer.parseInt(countNode);
                for (int i=1;i<=countNodeInt;i++){
                    nodeMap.put(i,new Node());
                }
                Scanner scanner1 = new Scanner(new File(nameFile));
                scanner1.nextLine();
                while (scanner1.hasNextLine()) {
                    String src="";
                    String dest="";
                    String weight="";
                    int space=0;
                    String line = scanner1.nextLine();
                    Scanner lineScanner = new Scanner(line);
                    if (lineScanner.hasNextLine()){
                        for (int i=0;i<line.length();i++){
                            if (line.charAt(i)==' '){
                                space++;
                            }
                            else if (space==0){
                                src+= line.charAt(i);
                            }
                            else if (space==1){
                                dest+= line.charAt(i);
                            }
                            else if (space==2){
                                weight+= line.charAt(i);
                            }
                            if (line.length()-1==i){
                                nodeMap.get(Integer.parseInt(src)).getLstEdge().add(new Edge(Integer.parseInt(src),Integer.parseInt(dest),Float.parseFloat(weight)));
                                nodeMap.get(Integer.parseInt(dest)).getLstEdge().add(new Edge(Integer.parseInt(dest),Integer.parseInt(src),Float.parseFloat(weight)));
                            }
                        }
                    }
                    lineScanner.close();
                }
                scanner1.close();
            }
            else if(create.equals("Матрица")){
                Scanner scanner = new Scanner(new File(nameFile));
                String countNode="";
                if (scanner.hasNextLine()){
                    String firstLine = scanner.nextLine();
                    Scanner lineScanner = new Scanner(firstLine);
                    if (lineScanner.hasNextLine()) {
                        countNode += lineScanner.nextLine();
                    }
                    lineScanner.close();
                }
                scanner.close();
                int countNodeInt=Integer.parseInt(countNode);
                for (int i=1;i<=countNodeInt;i++){
                    nodeMap.put(i,new Node());
                }
                Scanner scanner1 = new Scanner(new File(nameFile));
                scanner1.nextLine();
                int x=1;
                int y=1;
                while (scanner1.hasNextLine()) {
                    String weight = "";
                    String line = scanner1.nextLine();
                    Scanner lineScanner = new Scanner(line);
                    if (lineScanner.hasNextLine()) {
                        for (int i = 0; i < line.length(); i++) {
                            if (line.charAt(i)==' '){
                                if (!weight.equals("0")){
                                    if (!weight.contains(".")) {
                                        weight+=".0";
                                    }
                                    nodeMap.get(y).getLstEdge().add(new Edge(y,x,Float.parseFloat(weight)));
                                }
                                weight="";
                                x++;
                            }
                            else{
                                weight+=line.charAt(i);
                            }
                        }
                    }
                    y++;
                    x=1;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void saveGraph(String nameFile, String path, String save){
        try{
            if (save.equals("Списки")){
                FileWriter writer = new FileWriter(path+nameFile);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write(""+nodeMap.size()+"\n");
                for (int i=1;i<nodeMap.size();i++){
                    for (int j=0;j<nodeMap.get(i).getLstEdge().size();j++){
                        if (nodeMap.get(i).getLstEdge().get(j).getSrc()<=nodeMap.get(i).getLstEdge().get(j).getDest()){
                            bufferedWriter.write(""+nodeMap.get(i).getLstEdge().get(j).getSrc()+" "+nodeMap.get(i).getLstEdge().get(j).getDest()+" "+nodeMap.get(i).getLstEdge().get(j).getWeight()+"\n");
                        }
                    }
                }
                bufferedWriter.close();
            }
            else if (save.equals("Матрица")){
                FileWriter writer = new FileWriter(path+nameFile);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write(""+nodeMap.size()+"\n");
                for (int i=1;i<=nodeMap.size();i++){
                    List<String>lsrStr=new ArrayList<String>();
                    for (int j=0;j<nodeMap.size();j++){
                        lsrStr.add("0 ");
                    }
                    for (int j=0;j<nodeMap.get(i).getLstEdge().size();j++){
                        lsrStr.set(nodeMap.get(i).getLstEdge().get(j).getDest()-1,""+nodeMap.get(i).getLstEdge().get(j).getWeight()+" ");
                    }
                    for (int j=0;j<nodeMap.size();j++){
                        bufferedWriter.write(lsrStr.get(j));
                        if (j==nodeMap.size()-1){
                            bufferedWriter.write("\n");
                        }
                    }
                }
                bufferedWriter.close();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void addNode(){
        nodeMap.put(nodeMap.size()+1,new Node());
    }

    public void addEdge(int src, int dest, float weight){
        if (nodeMap.size()>=src && nodeMap.size()>=dest){
            nodeMap.get(src).getLstEdge().add(new Edge(src,dest,weight));
            nodeMap.get(dest).getLstEdge().add(new Edge(src,dest,weight));
        }
        else{
            throw new IllegalArgumentException("Таких вершин не существует");
        }
    }

    public void removeEdge(int src, int dest){
        if (nodeMap.size()>=src && nodeMap.size()>=dest){
            for (int i=0;i<nodeMap.get(src).getLstEdge().size();i++){
                if (nodeMap.get(src).getLstEdge().get(i).getSrc()==src && nodeMap.get(src).getLstEdge().get(i).getDest()==dest){
                    nodeMap.get(src).getLstEdge().remove(i);
                }
            }
            for (int i=0;i<nodeMap.get(dest).getLstEdge().size();i++){
                if (nodeMap.get(dest).getLstEdge().get(i).getSrc()==src && nodeMap.get(dest).getLstEdge().get(i).getDest()==dest){
                    nodeMap.get(dest).getLstEdge().remove(i);
                }
            }
        }
        else{
            throw new IllegalArgumentException("Таких вершин не существует");
        }
    }

    public int countNode(){
        return nodeMap.size();
    }

    public int countEdge(){
        int count=0;
        for (int i=1;i<=nodeMap.size();i++){
            for (int j=0;j<nodeMap.get(i).getLstEdge().size();j++){
                count++;
            }
        }
        return count/2;
    }

    public boolean checkAdjacency(int src, int dest){
        if (nodeMap.size()>=src && nodeMap.size()>=dest){
            boolean flag=false;
            for (int i=0;i<nodeMap.get(src).getLstEdge().size();i++){
                if (nodeMap.get(src).getLstEdge().get(i).getSrc()==dest || nodeMap.get(src).getLstEdge().get(i).getDest()==dest){
                    flag=true;
                    break;
                }
            }
            return flag;
        }
        else{
            throw new IllegalArgumentException("Таких вершин не существует");
        }
    }

    public float getWeight(int src, int dest){
        if (nodeMap.size()>=src && nodeMap.size()>=dest){
            for (int i=0;i<nodeMap.get(src).getLstEdge().size();i++){
                if (nodeMap.get(src).getLstEdge().get(i).getSrc()==dest || nodeMap.get(src).getLstEdge().get(i).getDest()==dest){
                    return nodeMap.get(src).getLstEdge().get(i).getWeight();
                }
            }
            return 0;
        }
        else{
            throw new IllegalArgumentException("Таких вершин не существует");
        }
    }

    public void sortListNode(String sort){
        List<Integer>lstDegrees=new ArrayList<Integer>();
        for (int i=1;i<=nodeMap.size();i++){
            lstDegrees.add(nodeMap.get(i).getLstEdge().size());
        }
        if (sort.equals("По возрастанию")){
            Collections.sort(lstDegrees);
            for (int i=0;i<lstDegrees.size();i++){
                System.out.print(lstDegrees.get(i)+" ");
            }
            System.out.println("\n");
        }
        else if(sort.equals("По убыванию")){
            Collections.sort(lstDegrees);
            for (int i=lstDegrees.size()-1;i>=0;i--){
                System.out.print(lstDegrees.get(i)+" ");
            }
            System.out.println("\n");
        }
    }
}
