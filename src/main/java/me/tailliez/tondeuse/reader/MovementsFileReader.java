package me.tailliez.tondeuse.reader;

import me.tailliez.tondeuse.data.Direction;
import me.tailliez.tondeuse.data.Place;
import me.tailliez.tondeuse.data.Position;
import me.tailliez.tondeuse.data.Program;
import me.tailliez.tondeuse.execution.ExecutionContext;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovementsFileReader {

    public final static Pattern LINE_FORMAT_LAWN_SIZE = Pattern.compile("(\\d+) (\\d+)");
    public final static Pattern LINE_FORMAT_INITIAL_POSITION = Pattern.compile("(\\d+) (\\d+) ([NESW])");
    public final static Pattern LINE_FORMAT_MOVES = Pattern.compile("([GDA]+)");

    private File inputFile;
    private Function<Integer, String> getName;

    public MovementsFileReader(String filename) throws ReadException {
        this(filename, (index) -> "Mower #" + index);
    }

    public MovementsFileReader(String filename, Function<Integer, String> makeName) throws ReadException {
        if (filename==null) {
            throw new ReadException("Le nom du fichier est null.");
        }
        this.inputFile = new File(filename);
        this.getName = makeName;
    }

    public List<ExecutionContext> read() throws FileNotFoundException, ReadException {
        List<ExecutionContext> contexts = new ArrayList<>();
        FileReader fileReader = null;
        LineNumberReader lineNumberReader = null;

        try {
            fileReader = new FileReader(inputFile);
            lineNumberReader = new LineNumberReader(fileReader);
            Place max = readLawnSize(lineNumberReader);
            contexts = readPrograms(lineNumberReader, max);
        } catch (ReadException e) {
            throw e;
        } catch (FileNotFoundException e) {
            throw new ReadException("Fichier \"" + inputFile.getAbsoluteFile().getName() + "\" non trouvé.", e);
        } catch (Exception e) {
            throw new ReadException("Erreur durant la lecture de la ligne " + (lineNumberReader.getLineNumber()) + ".", e);
        } finally {
            // Fermeture des readers
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (lineNumberReader != null) {
                try {
                    lineNumberReader.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        return contexts;
    }

    private Place readLawnSize(LineNumberReader reader) throws ReadException, IOException {
        // Première ligne : Coordonnées max
        String lineContent = reader.readLine();
        Matcher m = matchLine(lineContent, LINE_FORMAT_LAWN_SIZE, reader.getLineNumber());
        return new Place(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
    }

    private List<ExecutionContext> readPrograms(LineNumberReader reader, Place limit) throws ReadException, IOException {
        // Suite : Programmes des tondeuses
        List<ExecutionContext> contexts = new ArrayList<>();
        String lineContent;
        int mowerIndex = 1;
        while ((lineContent = reader.readLine()) != null) {

            // Position initiale
            Matcher init = matchLine(lineContent, LINE_FORMAT_INITIAL_POSITION, reader.getLineNumber());
            int x = Integer.parseInt(init.group(1));
            if (limit.x() < x) {
                throw new ReadException("Position de la tondeuse n°" + mowerIndex + " hors limite. Absisse " + x + " > " + limit.x() + ".");
            }
            int y = Integer.parseInt(init.group(2));
            if (limit.y() < y) {
                throw new ReadException("Position de la tondeuse n°" + mowerIndex + " hors limite. Ordonnée " + y + " > " + limit.y() + ".");
            }
            Direction d = Direction.getDirection(init.group(3).charAt(0));
            Position initialPosition = new Position(x, y, d);

            // Mouvements
            lineContent = reader.readLine();
            Matcher moves = matchLine(lineContent, LINE_FORMAT_MOVES, reader.getLineNumber());

            // Ajout du programme de la tondeuse courante
            Program program = new Program(getName.apply(mowerIndex), initialPosition, lineContent);
            ExecutionContext context = new ExecutionContext(limit, program);
            contexts.add(context);
            mowerIndex++;
        }
        return contexts;
    }

    private Matcher matchLine(String lineContent, Pattern regexp, int lineNumber) throws ReadException {
        if (lineContent == null) {
            throw new ReadException("La ligne " + (lineNumber + 1) + " n'existe pas. Le format attendu est \"" + regexp.pattern() + "\"");
        }
        Matcher m = regexp.matcher(lineContent);
        if (!m.matches()) {
            throw new ReadException("Le format de la ligne " + lineNumber + " n'est pas conforme. Le format attendu est \"" + regexp.pattern() + "\"");
        }
        return m;
    }

}
