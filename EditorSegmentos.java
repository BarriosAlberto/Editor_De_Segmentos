import java.util.ArrayList;

public class EditorSegmentos {
    private ArrayList<Segmento> segmentos;
    private final int INSIDE = 0; // 0000
    private final int LEFT = 1;   // 0001
    private final int RIGHT = 2;  // 0010
    private final int BOTTOM = 4; // 0100
    private final int TOP = 8;    // 1000

    private double xmin = 0, ymin = 0, xmax = 100, ymax = 100;

    public EditorSegmentos() {
        segmentos = new ArrayList<>();
    }

    public void adicionarSegmento(Segmento s) {
        segmentos.add(s);
    }

    public void transladarTodos(double dx, double dy) {
        for (Segmento s : segmentos) {
            s.translacao(dx, dy);
        }
    }

    public void rotacionarTodos90() {
        for (Segmento s : segmentos) {
            s.rotacao90();
        }
    }

    public void escalarTodos(double fator) {
        for (Segmento s : segmentos) {
            s.escala(fator);
        }
    }

    public void imprimirSegmentos() {
        for (Segmento s : segmentos) {
            System.out.println(s);
        }
    }

    private int calcularCodigo(double x, double y) {
        int codigo = INSIDE;

        if (x < xmin)
            codigo |= LEFT;
        else if (x > xmax)
            codigo |= RIGHT;
        if (y < ymin)
            codigo |= BOTTOM;
        else if (y > ymax)
            codigo |= TOP;

        return codigo;
    }

    public ArrayList<Segmento> aplicarRecorte() {
        ArrayList<Segmento> recortados = new ArrayList<>();
        for (Segmento s : segmentos) {
            double x1 = s.x1, y1 = s.y1, x2 = s.x2, y2 = s.y2;
            int codigo1 = calcularCodigo(x1, y1);
            int codigo2 = calcularCodigo(x2, y2);

            boolean aceito = false;

            while (true) {
                if ((codigo1 | codigo2) == 0) {
                    aceito = true;
                    break;
                } else if ((codigo1 & codigo2) != 0) {
                    break;
                } else {
                    double x = 0, y = 0;
                    int fora = (codigo1 != 0) ? codigo1 : codigo2;

                    if ((fora & TOP) != 0) {
                        x = x1 + (x2 - x1) * (ymax - y1) / (y2 - y1);
                        y = ymax;
                    } else if ((fora & BOTTOM) != 0) {
                        x = x1 + (x2 - x1) * (ymin - y1) / (y2 - y1);
                        y = ymin;
                    } else if ((fora & RIGHT) != 0) {
                        y = y1 + (y2 - y1) * (xmax - x1) / (x2 - x1);
                        x = xmax;
                    } else if ((fora & LEFT) != 0) {
                        y = y1 + (y2 - y1) * (xmin - x1) / (x2 - x1);
                        x = xmin;
                    }

                    if (fora == codigo1) {
                        x1 = x;
                        y1 = y;
                        codigo1 = calcularCodigo(x1, y1);
                    } else {
                        x2 = x;
                        y2 = y;
                        codigo2 = calcularCodigo(x2, y2);
                    }
                }
            }

            if (aceito) {
                recortados.add(new Segmento(x1, y1, x2, y2));
            }
        }
        return recortados;
    }
}