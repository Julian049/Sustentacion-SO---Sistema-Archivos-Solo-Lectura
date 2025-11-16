public class Simulador {
    private SquashFS squash = new SquashFS();

    public Simulador() {
        System.out.println("=== Simulador de SquashFS iniciado ===\n");

        System.out.println("--- Creando archivos iniciales ---");
        File file1 = new File("/home/user/documento.txt", "documento en blanco");
        File file2 = new File("/home/user/imagen.png", "imagen en blanco");
        File file3 = new File("/home/user/video.mp4", "video en blanco");
        File file4 = new File("/home/user/datos.json", "datos en formato JSON");
        File file5 = new File("/home/user/config.xml", "configuración XML");

        System.out.println("Agregando archivos al sistema de archivos SquashFS...");
        squash.addFile(file1);
        squash.addFile(file2);
        squash.addFile(file3);
        squash.addFile(file4);
        squash.addFile(file5);

        System.out.println("\nArchivos en el sistema antes de construir la imagen:");
        squash.seeAllFiles();


        System.out.println("\n--- Construyendo imagen SquashFS ---");
        squash.buildImage();


        System.out.println("\n--- Verificando inmutabilidad ---");

        System.out.println("\nIntentando modificar un archivo existente...");
        squash.modifyFile("/home/user/documento.txt", "El documento ya no está en blanco");

        System.out.println("\nIntentando agregar un nuevo archivo...");
        File file6 = new File("/home/user/nuevo_documento.txt", "Documento nuevo");
        squash.addFile(file6);

        System.out.println("\nIntentando modificar otro archivo...");
        squash.modifyFile("/home/user/imagen.png", "imagen modificada");


        System.out.println("\n--- Recreando sistema de archivos ---");
        System.out.println("Creando nueva instancia de SquashFS...");
        squash = new SquashFS();

        System.out.println("\nCreando nuevos archivos para la nueva imagen...");
        File file7 = new File("/home/admin/reporte.txt", "reporte mensual");
        File file8 = new File("/home/admin/logs.log", "logs del sistema");
        File file9 = new File("/home/admin/backup.tar", "backup comprimido");

        System.out.println("Agregando archivos ANTES de construir la nueva imagen...");
        squash.addFile(file7);
        squash.addFile(file8);
        squash.addFile(file9);

        System.out.println("\nModificando archivo antes de construir imagen...");
        squash.modifyFile("/home/admin/reporte.txt", "reporte mensual actualizado");

        System.out.println("\n Agregando más archivos...");
        File file10 = new File("/home/admin/notas.md", "notas importantes");
        squash.addFile(file10);

        System.out.println("\n Modificando otro archivo...");
        squash.modifyFile("/home/admin/notas.md", "notas muy importantes con cambios");

        System.out.println("\nArchivos en el sistema antes de construir la segunda imagen:");
        squash.seeAllFiles();

        System.out.println("\n--- FASE 5: Construyendo segunda imagen SquashFS ---");
        squash.buildImage();

        System.out.println("\n Intentando modificar después de la segunda construcción...");
        squash.modifyFile("/home/admin/reporte.txt", "intento de modificación posterior");

        System.out.println("\n=== Simulación finalizada ===");
    }
}
