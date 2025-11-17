public class Simulador {
    private SquashFS squash = new SquashFS();

    /*
    Constructor que inicializa y ejecuta toda la simulación.
    Se divide en dos ciclos principales para demostrar el comportamiento
    antes y después de construir la imagen (modo solo lectura).
    */
    public Simulador() {
        System.out.println("=== Simulacion SquashFS (solo lectura tras build) ===\n");

        System.out.println("[ESTADO INICIAL]");
        squash.showStatistics();

        System.out.println("\nOperaciones ANTES de construir la imagen");
        System.out.println("Creando hasta 3 archivos y probando modificaciones.");

        //Creación de instancias de archivos
        File file1 = new File("/home/user/doc1.txt", "contenido del doc1");
        File file2 = new File("/home/user/doc2.txt", "contenido doc2");
        File file3 = new File("/home/user/doc3.txt", "contenido del  documento3");

        //Añadir archivos al sistema (modo escritura habilitado)
        System.out.println("\nAgregando archivos...");
        squash.addFile(file1);
        squash.addFile(file2);
        squash.addFile(file3);

        //Modificar un archivo (modo escritura habilitado)
        System.out.println("\nModificando uno de los archivos...");
        squash.modifyFile("/home/user/doc2.txt", "doc2 modificado antes del build");

        //Eliminar un archivo (modo escritura habilitado)
        System.out.println("\nEliminando un archivo...");
        squash.deleteFile("/home/user/doc3.txt");

        //Añadir un nuevo archivo
        System.out.println("\nCreando un nuevo archivo...");
        File file4 = new File("/home/user/doc4.txt", "nuevo archivo antes del build");
        squash.addFile(file4);

        System.out.println("\nEstado de los archivos ANTES del primer build:");
        squash.seeAllFiles();

        //Construcción de la imagen. A partir de aquí, se vuelve de solo lectura.
        System.out.println("\nConstruyendo PRIMERA imagen SquashFS...");
        squash.buildImage();

        //Intento de modificar (debe fallar)
        System.out.println("\nIntentando modificar /home/user/doc1.txt...");
        squash.modifyFile("/home/user/doc1.txt", "modificación NO permitida después del build");

        //Intento de agregar (debe fallar)
        System.out.println("\nIntentando agregar un nuevo archivo /home/user/doc5.txt...");
        File file5 = new File("/home/user/doc5.txt", "archivo creado después del build");
        squash.addFile(file5);

        //Intento de eliminar (debe fallar)
        System.out.println("\nIntentando eliminar /home/user/doc2.txt...");
        squash.deleteFile("/home/user/doc2.txt");

        System.out.println("\n Estado de archivos DESPUÉS del primer build:");
        squash.seeAllFiles();

        System.out.println("\nEstadísticas después del primer ciclo:");
        squash.showStatistics();

        // --- INICIO DEL SEGUNDO CICLO DE SIMULACIÓN ---
        System.out.println("\nRecreando el sistema de archivos SquashFS");

        squash = new SquashFS();

        //Se crea una nueva instancia para simular un nuevo sistema
        System.out.println("Creando archivos en la NUEVA instancia ANTES del build");
        File fileA = new File("/var/log/syslog.log", "logs del sistema");
        File fileB = new File("/var/log/auth.log", "logs de autenticación");
        File fileC = new File("/var/backups/backup.tar", "backup");

        System.out.println("\nAgregando 3 archivos...");
        squash.addFile(fileA);
        squash.addFile(fileB);
        squash.addFile(fileC);

        System.out.println("\nModificando uno de los archivos...");
        squash.modifyFile("/var/backups/backup.tar", "backup actualizado antes del build");

        System.out.println("\nEliminando un archivo para liberar espacio...");
        squash.deleteFile("/var/log/auth.log");

        System.out.println("\nAgregando un nuevo archivo para seguir teniendo máximo 3...");
        File fileD = new File("/var/log/kernel.log", "logs del kernel");
        squash.addFile(fileD);

        System.out.println("\nEstado de archivos ANTES del segundo build:");
        squash.seeAllFiles();

        System.out.println("\n Construyendo SEGUNDA imagen SquashFS...");
        squash.buildImage();

        System.out.println("\nIntentos de cambio DESPUÉS del segundo build (deben fallar otra vez)");

        System.out.println("\nIntentando modificar /var/log/syslog.log...");
        squash.modifyFile("/var/log/syslog.log", "modificación posterior al segundo build (no permitida)");

        System.out.println("\nIntentando agregar /var/log/nuevo.log...");
        File fileE = new File("/var/log/nuevo.log", "nuevo archivo después del segundo build");
        squash.addFile(fileE);

        System.out.println("\nIntentando eliminar /var/backups/backup.tar...");
        squash.deleteFile("/var/backups/backup.tar");

        System.out.println("\nEstado final de archivos tras el segundo build:");
        squash.seeAllFiles();


        System.out.println("\nEstadísticas FINAL de la simulación:");
        squash.showStatistics();

    }

}

