import java.util.ArrayList;

public class SquashFS {
    private ArrayList<File> files;
    private boolean imageBuild;
    private int totalSize;
    private int compressedSize;

    public void addFile(File file) {
        if (files == null) {
            files = new ArrayList<>();
        }
        if (!imageBuild) {
            files.add(file);
            totalSize += file.getSize();
        } else {
            System.out.println("La imagen ya fue construida, no se pueden agregar más archivos.");
        }
    }

    public void buildImage() {
        System.out.println("Construyendo la imagen SquashFS...");
        imageBuild = true;
        System.out.println("Calculando tamaño total de archivos...");
        System.out.println("Comprimendo archivos...");
        compressedSize = (int) (totalSize * 0.6);
        System.out.println("Total tamaño de archivos: " + totalSize + " bytes");
        System.out.println("Tamaño comprimido de la imagen SquashFS: " + compressedSize + " bytes");
        System.out.println("Se hanc comprimido los archivos al 60% de su tamaño original.");
        System.out.println("Imagen SquashFS construida con éxito.");

    }

    public File searchFile(String path) {
        if (files != null) {
            for (File file : files) {
                if (file.getPath().equals(path)) {
                    return file;
                }
            }
        }
        return null;
    }

    public void modifyFile(String path, String newContent) {
        if (imageBuild) {
            System.out.println("No se pueden modificar archivos en una imagen SquashFS ya construida.");
        } else {
            if (searchFile(path) == null) {
                System.out.println("Archivo no encontrado: " + path);
                return;
            }
            File file = searchFile(path);
            file.setContent(newContent);
            file.setSize(newContent.length());
            System.out.println("Archivo modificado: " + file.getPath());
        }
    }

    public void deleteFile(String path) {
        if (imageBuild) {
            System.out.println("No se pueden eliminar archivos en una imagen SquashFS que ya esta construida.");
        } else {
            File file = searchFile(path);
            if (file != null) {
                files.remove(file);
                totalSize -= file.getSize();
                System.out.println("Archivo eliminado: " + file.getPath());
            } else {
                System.out.println("Archivo no encontrado: " + path);
            }
        }
    }

    public void seeAllFiles() {
        if (files != null) {
            for (File file : files) {
                System.out.println("Archivo: " + file.getPath() + ", Tamaño: " + file.getSize() + " bytes");
            }
        } else {
            System.out.println("No hay archivos en el sistema de archivos SquashFS.");
        }
    }
}
