package GraphLab.graphComponent;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private List<Edge> lstEdge;

    public Node(){
        lstEdge=new ArrayList<Edge>();
    }

    public List<Edge> getLstEdge() {
        return lstEdge;
    }

    public void setLstEdge(List<Edge> lstEdge) {
        this.lstEdge = lstEdge;
    }
}
