public class Segmento {
    public double x1, y1, x2, y2;

    public Segmento(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void translacao(double dx, double dy) {
        x1 += dx; y1 += dy;
        x2 += dx; y2 += dy;
    }

    public void rotacao90() {
        double newX1 = -y1;
        double newY1 = x1;
        double newX2 = -y2;
        double newY2 = x2;
        x1 = newX1; y1 = newY1;
        x2 = newX2; y2 = newY2;
    }

    public void escala(double fator) {
        x1 *= fator; y1 *= fator;
        x2 *= fator; y2 *= fator;
    }

    public double[] getPontos() {
        return new double[]{x1, y1, x2, y2};
    }

    @Override
    public String toString() {
        return String.format("[(%.2f, %.2f), (%.2f, %.2f)]", x1, y1, x2, y2);
    }
}
