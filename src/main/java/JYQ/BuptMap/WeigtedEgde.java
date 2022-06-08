package JYQ.BuptMap;

import java.io.Serializable;

public class WeigtedEgde implements Serializable {
    private int AdjcentVertexNumber;
    private double weight;
    public WeigtedEgde(int V1, double W) {
        AdjcentVertexNumber = V1;
        weight = W;
    }

    public int getAdjacentVertexNumber() {
        return AdjcentVertexNumber;
    }

    public void setAdjacentVertexNumber(int adjcentVertexNumber) {
        AdjcentVertexNumber = adjcentVertexNumber;
    }

    public double getWeight() {
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
