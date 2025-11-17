import java.util.ArrayList;

public class SquashFS {
    private ArrayList<File> files;
    private boolean imageBuild;
    private int totalSize;
    private int compressedSize;
    private static final long MAX_SIZE = 214748364;

    public void addFile(File file) {
        if (files == null) {
            files = new ArrayList<>();
        }
        if (!imageBuild) {
            if (file.getSize() + totalSize <= MAX_SIZE) {
                files.add(file);
                totalSize += file.getSize();
                System.out.println("Archivo agregado: " + file.getPath() + " (Tamaño: " + file.getSize() + " bytes)");
            } else {
                System.out.println("No se puede agregar el archivo " + file.getPath() + ". Excede el tamaño máximo de la imagen SquashFS.");
            }
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
        double compressionRatio = ((double) (totalSize - compressedSize) / totalSize) * 100;
        System.out.println("Total tamaño de archivos: " + totalSize + " bytes");
        System.out.println("Tamaño comprimido de la imagen SquashFS: " + compressedSize + " bytes");
        System.out.println("Se han comprimido los archivos al " + (100 - compressionRatio) + "% de su tamaño original.");
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
                System.out.println("├─ Archivo: " + file.getPath());
                System.out.println("│  ├─ Tamaño: " + file.getSize() + " bytes");
                System.out.println("│  ├─ Contenido: " + file.getContent());
                System.out.println("│  ├─ Fecha creación: " + file.getDateCreated());
                System.out.println("│  └─ Inodo: " + file.getInode());
                System.out.println("│");
            }
            System.out.println("└─ Total archivos: " + files.size());
        } else {
            System.out.println("No hay archivos en el sistema de archivos SquashFS.");
        }
    }

    public void showStatistics() {
        System.out.println("=======ESTADÍSTICAS DEL SISTEMA SQUASHFS=======");
        System.out.println("Estado de la imagen: " + (imageBuild ? "CONSTRUIDA (solo lectura)" : "NO CONSTRUIDA (lectura y escritura)"));
        System.out.println("Total de archivos: " + (files != null ? files.size() : 0));
        System.out.println("Tamaño total sin comprimir: " + totalSize + " bytes");

        if (imageBuild) {
            System.out.println("Tamaño comprimido: " + compressedSize + " bytes");
            System.out.println("Espacio ahorrado: " + (totalSize - compressedSize) + " bytes");
        }
    }
}
