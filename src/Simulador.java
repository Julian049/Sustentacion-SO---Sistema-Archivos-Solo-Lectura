public class Simulador {
    private SquashFS squash = new SquashFS();

    public Simulador() {
        System.out.println("Simulador de SquashFS iniciado.");

        System.out.println("Creando archivos...");
        File file1 = new File("/home/user/documento.txt", "documento en blanco");
        File file2 = new File("/home/user/imagen.png", "imagen en blanco");
        File file3 = new File("/home/user/video.mp4", "video en blanco");

        System.out.println("Agregando archivos al sistema de archivos SquashFS...");
        squash.addFile(file1);
        squash.addFile(file2);
        squash.addFile(file3);

        squash.seeAllFiles();

        System.out.println("Construyendo sistema de archivos SquashFS...");
        squash.buildImage();

        System.out.println("Intentando modificar un archivo...");
        squash.modifyFile("/home/user/documento.txt", "El documento ya no esta en blanco");

    }
}
