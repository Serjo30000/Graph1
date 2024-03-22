package GraphLab.graphComponent;

public class Edge {
    private int src;
    private int dest;
    private float weight;
    public Edge(int src, int dest, float weight){
        this.setSrc(src);
        this.setDest(dest);
        this.setWeight(weight);
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public int getDest() {
        return dest;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
