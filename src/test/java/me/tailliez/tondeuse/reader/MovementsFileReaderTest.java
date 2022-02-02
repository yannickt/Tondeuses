package me.tailliez.tondeuse.reader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MovementsFileReaderTest {

    @Test
    void test_filename_missing() {
        ReadException readException = Assertions.assertThrows(ReadException.class, () -> new MovementsFileReader(null));
        Assertions.assertEquals("Le nom du fichier est null.", readException.getMessage());
    }

    @Test
    void test_file_missing() {
        try {
            MovementsFileReader reader = new MovementsFileReader("foo.bar");
            ReadException readException = Assertions.assertThrows(ReadException.class, reader::read);
            Assertions.assertEquals("Fichier \"foo.bar\" non trouvé.", readException.getMessage());
        } catch (Exception e) {
            Assertions.fail("Exception non prévue !");
        }
    }

    @Test
    void test_line1_incomplete() {
        try {
            String file = ClassLoader.getSystemClassLoader().getResource("reader/test_line1_incomplete.txt").getFile();
            MovementsFileReader reader = new MovementsFileReader(file);
            ReadException readException = Assertions.assertThrows(ReadException.class, reader::read);
            Assertions.assertEquals("Le format de la ligne 1 n'est pas conforme. Le format attendu est \""+MovementsFileReader.LINE_FORMAT_LAWN_SIZE+"\"", readException.getMessage());
        } catch (Exception e) {
            Assertions.fail("Exception non prévue !");
        }
    }

    @Test
    void test_line1_missing() {
        try {
            String file = ClassLoader.getSystemClassLoader().getResource("reader/test_line1_missing.txt").getFile();
            MovementsFileReader reader = new MovementsFileReader(file);
            ReadException readException = Assertions.assertThrows(ReadException.class, reader::read);
            Assertions.assertEquals("La ligne 1 n'existe pas. Le format attendu est \""+MovementsFileReader.LINE_FORMAT_LAWN_SIZE+"\"", readException.getMessage());
        } catch (Exception e) {
            Assertions.fail("Exception non prévue !");
        }
    }

    @Test
    void test_line1_x_letter() {
        try {
            String file = ClassLoader.getSystemClassLoader().getResource("reader/test_line1_x_letter.txt").getFile();
            MovementsFileReader reader = new MovementsFileReader(file);
            ReadException readException = Assertions.assertThrows(ReadException.class, reader::read);
            Assertions.assertEquals("Le format de la ligne 1 n'est pas conforme. Le format attendu est \""+MovementsFileReader.LINE_FORMAT_LAWN_SIZE+"\"", readException.getMessage());
        } catch (Exception e) {
            Assertions.fail("Exception non prévue !");
        }
    }

    @Test
    void test_line1_x_negative() {
        try {
            String file = ClassLoader.getSystemClassLoader().getResource("reader/test_line1_x_negative.txt").getFile();
            MovementsFileReader reader = new MovementsFileReader(file);
            ReadException readException = Assertions.assertThrows(ReadException.class, reader::read);
            Assertions.assertEquals("Le format de la ligne 1 n'est pas conforme. Le format attendu est \""+MovementsFileReader.LINE_FORMAT_LAWN_SIZE+"\"", readException.getMessage());
        } catch (Exception e) {
            Assertions.fail("Exception non prévue !");
        }
    }

    @Test
    void test_line2_bad_value_1() {
        try {
            String file = ClassLoader.getSystemClassLoader().getResource("reader/test_line2_bad_value_1.txt").getFile();
            MovementsFileReader reader = new MovementsFileReader(file);
            ReadException readException = Assertions.assertThrows(ReadException.class, reader::read);
            Assertions.assertEquals("Le format de la ligne 2 n'est pas conforme. Le format attendu est \""+MovementsFileReader.LINE_FORMAT_INITIAL_POSITION+"\"", readException.getMessage());
        } catch (Exception e) {
            Assertions.fail("Exception non prévue !");
        }
    }

    @Test
    void test_line2_bad_value_2() {
        try {
            String file = ClassLoader.getSystemClassLoader().getResource("reader/test_line2_bad_value_2.txt").getFile();
            MovementsFileReader reader = new MovementsFileReader(file);
            ReadException readException = Assertions.assertThrows(ReadException.class, reader::read);
            Assertions.assertEquals("Le format de la ligne 2 n'est pas conforme. Le format attendu est \""+MovementsFileReader.LINE_FORMAT_INITIAL_POSITION+"\"", readException.getMessage());
        } catch (Exception e) {
            Assertions.fail("Exception non prévue !");
        }
    }

    @Test
    void test_line2_bad_value_3() {
        try {
            String file = ClassLoader.getSystemClassLoader().getResource("reader/test_line2_bad_value_3.txt").getFile();
            MovementsFileReader reader = new MovementsFileReader(file);
            ReadException readException = Assertions.assertThrows(ReadException.class, reader::read);
            Assertions.assertEquals("Le format de la ligne 2 n'est pas conforme. Le format attendu est \""+MovementsFileReader.LINE_FORMAT_INITIAL_POSITION+"\"", readException.getMessage());
        } catch (Exception e) {
            Assertions.fail("Exception non prévue !");
        }
    }

    @Test
    void test_line2_incomplete() {
        try {
            String file = ClassLoader.getSystemClassLoader().getResource("reader/test_line2_incomplete.txt").getFile();
            MovementsFileReader reader = new MovementsFileReader(file);
            ReadException readException = Assertions.assertThrows(ReadException.class, reader::read);
            Assertions.assertEquals("Le format de la ligne 2 n'est pas conforme. Le format attendu est \""+MovementsFileReader.LINE_FORMAT_INITIAL_POSITION+"\"", readException.getMessage());
        } catch (Exception e) {
            Assertions.fail("Exception non prévue !");
        }
    }

    @Test
    void test_line3_bad_value() {
        try {
            String file = ClassLoader.getSystemClassLoader().getResource("reader/test_line3_bad_value.txt").getFile();
            MovementsFileReader reader = new MovementsFileReader(file);
            ReadException readException = Assertions.assertThrows(ReadException.class, reader::read);
            Assertions.assertEquals("Le format de la ligne 3 n'est pas conforme. Le format attendu est \""+MovementsFileReader.LINE_FORMAT_MOVES+"\"", readException.getMessage());
        } catch (Exception e) {
            Assertions.fail("Exception non prévue !");
        }
    }

    @Test
    void test_line3_empty() {
        try {
            String file = ClassLoader.getSystemClassLoader().getResource("reader/test_line3_empty.txt").getFile();
            MovementsFileReader reader = new MovementsFileReader(file);
            ReadException readException = Assertions.assertThrows(ReadException.class, reader::read);
            Assertions.assertEquals("Le format de la ligne 3 n'est pas conforme. Le format attendu est \""+MovementsFileReader.LINE_FORMAT_MOVES+"\"", readException.getMessage());
        } catch (Exception e) {
            Assertions.fail("Exception non prévue !");
        }
    }

    @Test
    void test_line3_missing() {
        try {
            String file = ClassLoader.getSystemClassLoader().getResource("reader/test_line3_missing.txt").getFile();
            MovementsFileReader reader = new MovementsFileReader(file);
            ReadException readException = Assertions.assertThrows(ReadException.class, reader::read);
            Assertions.assertEquals("La ligne 3 n'existe pas. Le format attendu est \""+MovementsFileReader.LINE_FORMAT_MOVES+"\"", readException.getMessage());
        } catch (Exception e) {
            Assertions.fail("Exception non prévue !");
        }
    }
}