/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pengelolafile;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class FileSystem {

    private TreeNode root;

    public FileSystem() {
        root = new TreeNode("document", true);
    }

    public TreeNode getRoot() {
        return root;
    }

    public void createFolder(String path) {
        try {
            validatePath(path);
            String[] folders = path.split("/");
            TreeNode current = root;

            for (String folder : folders) {
                if (folder.isEmpty()) {
                    continue;
                }

                validateFolderName(folder);

                TreeNode newFolder = findChildFolder(current, folder);
                if (newFolder != null) {
                    current = newFolder;
                } else {
                    TreeNode folderNode = new TreeNode(folder, true);
                    current.addChild(folderNode);
                    current = folderNode;
                }
            }
        } catch (IllegalArgumentException e) {
            handleException(e);
        }
    }

    public void createFile(String filePath, String content) {
        try {
            validatePath(filePath);
            validateFileExtension(filePath);

            String[] directories = filePath.split("/");
            if (directories.length == 1) {
                throw new IllegalArgumentException("File harus dibuat di dalam folder yang sudah ada");
            }

            String fileName = directories[directories.length - 1];
            TreeNode current = root;

            for (int i = 0; i < directories.length - 1; i++) {
                String directory = directories[i];
                TreeNode folder = findChildFolder(current, directory);
                if (folder != null) {
                    current = folder;
                } else {
                    throw new IllegalArgumentException("Folder tidak valid: " + directory);
                }
            }

            validateFileName(fileName);
            TreeNode fileNode = new TreeNode(fileName, false);
            fileNode.setContent(content);
            current.addChild(fileNode);

            System.out.println("File berhasil dibuat");
        } catch (IllegalArgumentException e) {
            handleException(e);
        }
    }

    public void deleteFile(String filePath) {
        try {
            validatePath(filePath);

            String[] directories = filePath.split("/");
            if (directories.length == 0) {
                throw new IllegalArgumentException("Path tidak valid");
            }

            String fileName = directories[directories.length - 1];
            TreeNode current = root;

            for (int i = 0; i < directories.length - 1; i++) {
                String directory = directories[i];
                TreeNode folder = findChildFolder(current, directory);
                if (folder != null) {
                    current = folder;
                } else {
                    throw new IllegalArgumentException("Path tidak valid");
                }
            }

            TreeNode fileToDelete = findChildFolder(current, fileName);
            if (fileToDelete != null && !fileToDelete.isFolder()) {
                removeChild(current, fileToDelete);
                System.out.println("File dihapus");
            } else {
                throw new IllegalArgumentException("File tidak ditemukan: " + filePath);
            }
        } catch (IllegalArgumentException e) {
            handleException(e);
        }
    }

    public void deleteFolder(String path) {
        try {
            validatePath(path);

            String[] folders = path.split("/");
            TreeNode current = root;
            TreeNode parent = null;

            for (String folder : folders) {
                if (folder.isEmpty()) {
                    continue;
                }

                parent = current;
                current = findChildFolder(current, folder);
                if (current == null) {
                    throw new IllegalArgumentException("Path tidak valid");
                }
            }

            if (parent != null) {
                removeChild(parent, current);
                System.out.println("Folder dihapus");
            } else {
                throw new IllegalArgumentException("Tidak dapat menghapus folder root");
            }
        } catch (IllegalArgumentException e) {
            handleException(e);
        }
    }

    public void search(String name) {
        List<String> results = new LinkedList<>();
        searchHelper(root, "", name, results);

        if (results.isEmpty()) {
            System.out.println("Tidak ditemukan file atau folder dengan nama: " + name);
        } else {
            for (String result : results) {
                System.out.println(result);
            }
        }
    }

    public String getTreeStructure() {
        StringBuilder treeStructure = new StringBuilder();
        displayTreeStructureHelper(root, "", treeStructure);
        return treeStructure.toString();
    }

    public void displayTreeStructure() {
        System.out.println(getTreeStructure());
    }

    private void validatePath(String path) {
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("Path tidak valid");
        }
    }

    private void validateFolderName(String folderName) {
        String[] invalidCharacters = {"\\", "*", "|", "^"};
        for (String character : invalidCharacters) {
            if (folderName.contains(character)) {
                throw new IllegalArgumentException("Nama folder mengandung karakter tidak valid");
            }
        }
    }

    private void validateFileName(String fileName) {
        String[] invalidCharacters = {"\\", "*", "|", "^"};
        for (String character : invalidCharacters) {
            if (fileName.contains(character)) {
                throw new IllegalArgumentException("Nama file mengandung karakter tidak valid");
            }
        }
    }

    private void validateFileExtension(String filePath) {
        if (!(filePath.toLowerCase().endsWith(".txt") || filePath.toLowerCase().endsWith(".docx") || filePath.toLowerCase().endsWith(".pdf"))) {
            throw new IllegalArgumentException("Ekstensi file tidak valid");
        }
    }

    private TreeNode findChildFolder(TreeNode node, String folderName) {
        if (node == null) {
            return null;
        }

        for (TreeNode child : node.getChildren()) {
            if (child.getName().equals(folderName) && child.isFolder()) {
                return child;
            }
        }

        return null;
    }

    private void removeChild(TreeNode parent, TreeNode child) {
        parent.getChildren().remove(child);
    }

    private void searchHelper(TreeNode node, String path, String name, List<String> results) {
        if (node == null) {
            return;
        }

        if (node.getName().equals(name)) {
            results.add(path + "/" + node.getName() + (node.isFolder() ? " (Folder)" : " (File)"));
        }

        for (TreeNode child : node.getChildren()) {
            searchHelper(child, path + "/" + node.getName(), name, results);
        }
    }

    private void displayTreeStructureHelper(TreeNode node, String indent, StringBuilder treeStructure) {
        if (node == null) {
            return;
        }

        treeStructure.append(indent).append(node.getName()).append(node.isFolder() ? " (Folder)" : " (File)").append("\n");

        for (TreeNode child : node.getChildren()) {
            displayTreeStructureHelper(child, indent + "  ", treeStructure);
        }
    }

    private void handleException(IllegalArgumentException e) {
        System.err.println(e.getMessage());
    }
    

}
