package JYQ.BuptMap;

public class WeigtedEgde {
    private int VertexNumber;
    private int weight;
    public WeigtedEgde(int V, int W) {
        VertexNumber = V;
        weight = W;
    }
    public int getVertexNumber() {
        return VertexNumber;
    }
    public int getWeight() {
        return weight;
    }
    public void setVertexNumber(int V) {
        this.VertexNumber = V;
    }
    public void setWeight(int W) {
        this.weight = W;
    }
}
