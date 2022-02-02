package me.tailliez.tondeuse;

class LauncherTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void main() {
        String file = ClassLoader.getSystemClassLoader().getResource("test1.txt").getFile();
        Launcher.main(file);
    }
}