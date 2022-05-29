package JYQ.BuptMap;

public class WeigtedEgde {
    private int AdjcentVertexNumber;
    private int weight;
    public WeigtedEgde(int V1, int W) {
        AdjcentVertexNumber = V1;
        weight = W;
    }

    public int getAdjacentVertexNumber() {
        return AdjcentVertexNumber;
    }

    public void setAdjacentVertexNumber(int adjcentVertexNumber) {
        AdjcentVertexNumber = adjcentVertexNumber;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof WeigtedEgde) {
            WeigtedEgde weigtedEgde = (WeigtedEgde) obj;
            if (weigtedEgde.AdjcentVertexNumber == this.AdjcentVertexNumber && weigtedEgde.weight == this.weight) {
                return true;
            }
            return false;
        }
        return false;
    }
}
