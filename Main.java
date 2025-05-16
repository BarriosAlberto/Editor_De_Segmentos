public class Main {
    public static void main(String[] args) {
        EditorSegmentos editor = new EditorSegmentos();

        // Adiciona segmentos 
        editor.adicionarSegmento(new Segmento(10, 10, 90, 90));
        editor.adicionarSegmento(new Segmento(50, 120, 120, 50));
        editor.adicionarSegmento(new Segmento(0, 0, 200, 200));

        System.out.println("Segmentos originais:");
        editor.imprimirSegmentos();

        System.out.println("\nSegmentos após recorte:");
        for (Segmento s : editor.aplicarRecorte()) {
            System.out.println(s);
        }

        // translação
        editor.transladarTodos(10, 10);
        System.out.println("\nSegmentos após translação:");
        editor.imprimirSegmentos();

        // rotação 90 graus
        editor.rotacionarTodos90();
        System.out.println("\nSegmentos após rotação 90 graus:");
        editor.imprimirSegmentos();

        // escala
        editor.escalarTodos(0.5);
        System.out.println("\nSegmentos após escala 0.5:");
        editor.imprimirSegmentos();
    }
}
