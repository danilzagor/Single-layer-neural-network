import org.w3c.dom.traversal.TreeWalker;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataRepository {
    private final List<TextEntity> texts = new ArrayList<>();
    private final List<String> languages = new ArrayList<>();

    public DataRepository(String dirName) {
        createListOfParameters(dirName);
    }

    private void createListOfParameters(String dirName) {
        try {
            Files.walkFileTree(Paths.get(dirName), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String text = String.join("", Files.readAllLines(file));
                    String language = String.valueOf(file.getParent().getFileName());
                    final TextEntity textEntity = new TextEntity(language,text);
                    texts.add(textEntity);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                    if(!String.valueOf(dir.getFileName()).equals("training"))
                        languages.add(String.valueOf(dir.getFileName()));
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TextEntity> getTexts() {
        return texts;
    }

    public List<String> getLanguages() {
        return languages;
    }
}
